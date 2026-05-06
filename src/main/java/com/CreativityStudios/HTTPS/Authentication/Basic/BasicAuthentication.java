package com.CreativityStudios.HTTPS.Authentication.Basic;

import com.CreativityStudios.Database.Users.UserEntry;
import com.CreativityStudios.Database.Users.UserDatabaseCalls;
import com.CreativityStudios.HTTPS.Authentication.AuthenticationResult;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPMethods;
import com.CreativityStudios.HTTPS.Sessions.*;
import com.CreativityStudios.HTTPS.Sessions.Cookie.CookieSessionManager;
import com.CreativityStudios.HTTPS.Sessions.Cookie.CookieToken;
import com.sun.net.httpserver.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BasicAuthentication extends Authenticator {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    @Override
    public Result authenticate(HttpExchange exchange) {
        Headers headers = exchange.getRequestHeaders();
        String method = exchange.getRequestMethod();
        Token token = new CookieToken();
        token.setToken(headers);

        if(method.equals(HTTPMethods.OPTIONS)) {
            return createSuccessToken("cors-policy", "cors");
        }

        if(CookieSessionManager.isSessionValid(token)) {
            UserSession userSession = CookieSessionManager.getSession(token);
            LOGGER.log(Level.INFO, "Authenticated by session");
            return createSuccessToken(userSession.getEmail(), userSession.getDomain());
        }

        try {
            AuthenticationResult result = checkCredentials(String.valueOf(headers.get("Authorization")));
            if(result.isAuthenticationSuccessful()) {
                CookieSessionManager.createSessionToken(exchange, result);
                LOGGER.log(Level.INFO, "Authenticated by username/password");

                return createSuccessToken(result.getUserEmail(), result.getUserDomain());
            } else {
                return new Failure(401);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public AuthenticationResult checkCredentials(String receivedCredentials) throws SQLException {
        if(receivedCredentials == null) {
            return new AuthenticationResult(false);
        }
        String b64Credentials = receivedCredentials.split(" ")[1].substring(0, receivedCredentials.split(" ")[1].length() - 1);
        byte[] byteCredentials = Base64.getDecoder().decode(b64Credentials);
        String userEmail = new String(byteCredentials).split(":")[0];
        String userPassword = new String(byteCredentials).split(":")[1];
        AuthenticationResult authResult;
        // TODO SECURE CREDENTIALS!!! Unhashing credentials reveal a runtime leak of passwords that are stored in memory
        ArrayList<UserEntry> usersList =
                UserDatabaseCalls.getAllUserEntriesFromUsersWithUserEmailAndPassword(userEmail, userPassword);

        if(usersList.size() == 1) {
            authResult = new AuthenticationResult(true, usersList.getFirst().getEmail(), usersList.getFirst().getDomain());
        } else {
            authResult = new AuthenticationResult(false);
        }

        return authResult;
    }

    public Result createSuccessToken(String username, String domain) {
        HttpPrincipal newAuthPrincipal = new HttpPrincipal(username, domain);

        return new Success(newAuthPrincipal);
    }
}
