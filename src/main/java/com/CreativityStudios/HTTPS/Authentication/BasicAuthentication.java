package com.CreativityStudios.HTTPS.Authentication;

import com.CreativityStudios.Database.UserEntry;
import com.CreativityStudios.Database.Users.UserDatabaseCalls;
import com.CreativityStudios.HTTPS.HTTPMethods;
import com.CreativityStudios.HTTPS.SessionManager;
import com.CreativityStudios.JSON.JSONReader;
import com.sun.net.httpserver.*;
import jakarta.json.JsonObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicAuthentication extends Authenticator {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final SessionManager sessions = new SessionManager();

    @Override
    public Result authenticate(HttpExchange exchange) {
        Headers headers = exchange.getRequestHeaders();
        String method = exchange.getRequestMethod();
        String session = null;

        if(method.equals(HTTPMethods.OPTIONS)) {
            return createSuccessToken("cors-policy", "cors");
        }

        if(headers.get("Cookie") != null) {
            String firstCookie = headers.get("Cookie").getFirst();
            session = firstCookie.substring(firstCookie.indexOf('=') + 1);
        }

        if(hasValidSessionToken(session)) {
            UserSession userSession = getSession(session);
            LOGGER.log(Level.INFO, userSession.toString());
            return createSuccessToken(userSession.getEmail(), userSession.getDomain());
        }

        try {
            AuthenticationResult result = checkCredentials(String.valueOf(headers.get("Authorization")));
            if(result.isAuthenticationSuccessful()) {
                createSessionCookie(exchange, result);
                LOGGER.log(Level.INFO, result.toString());

                return createSuccessToken(result.getUserEmail(), result.getUserDomain());
            } else {
                return new Failure(401);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthenticationResult checkCredentials(String receivedCredentials) throws SQLException {
        String b64Credentials = receivedCredentials.split(" ")[1].substring(0, receivedCredentials.split(" ")[1].length() - 1);
        byte[] byteCredentials = Base64.getDecoder().decode(b64Credentials);
        String userEmail = new String(byteCredentials).split(":")[0];
        String userPassword = new String(byteCredentials).split(":")[1];
        AuthenticationResult authResult;
        // TODO SECURE CREDENTIALS!!! Unhashing credentials reveal a runtime leak of passwords that are stored in memory
        ArrayList<UserEntry> usersList =
                UserDatabaseCalls.getAllUserEntriesFromUsersWithUserEmailAndPassword(userEmail, userPassword);

        if(usersList.size() == 1) {
            LOGGER.log(Level.INFO, "size = 1");
            authResult = new AuthenticationResult(true, usersList.getFirst().getEmail(), usersList.getFirst().getDomain());
        } else {
            authResult = new AuthenticationResult(false);
        }

        return authResult;
    }

    public boolean hasValidSessionToken(String session) {
        return sessions.isSessionValid(session);
    }

    public Result createSuccessToken(String username, String domain) {
        HttpPrincipal newAuthPrincipal = new HttpPrincipal(username, domain);

        return new Success(newAuthPrincipal);
    }

    public void createSessionCookie(HttpExchange exchange, AuthenticationResult authResult) {
        String newSession = sessions.getValidSessionToken();
        UserSession addedSession = sessions.add(newSession, authResult);
        LOGGER.log(Level.INFO, "added session to cache");
        exchange.getResponseHeaders().add("Set-Cookie", "session=" + newSession + "; expires=" + addedSession.getExpiryTime() + "; SameSite=none; Secure");
    }

    private UserSession getSession(String session) {
        return sessions.getSession(session);
    }
}
