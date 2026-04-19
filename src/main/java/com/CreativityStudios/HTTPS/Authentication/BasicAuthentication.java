package com.CreativityStudios.HTTPS.Authentication;

import com.CreativityStudios.HTTPS.HTTPMethods;
import com.CreativityStudios.HTTPS.HTTPStatus;
import com.CreativityStudios.HTTPS.SessionManager;
import com.sun.net.httpserver.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;
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
            session = headers.get("Cookie").getFirst();
        }

        if(hasValidSessionToken(session)) {
            return createSuccessToken("test", "testing");
        }

       if(isCredentialsValid(String.valueOf(headers.get("Authorization")))) {
           createSessionCookie(exchange);
           return createSuccessToken("test", "testing");
       } else {
           return new Failure(401);
       }
    }

    public boolean isCredentialsValid(String receivedCredentials) {
        String credentials = Base64.getEncoder().encodeToString( "test:test".getBytes());

        return credentials.equals(receivedCredentials.split(" ")[1].substring(0, receivedCredentials.split(" ")[1].length() - 1));
    }

    public boolean hasValidSessionToken(String session) {
        return sessions.isSessionValid(session);
    }

    public Result createSuccessToken(String username, String domain) {
        HttpPrincipal newAuthPrincipal = new HttpPrincipal(username, domain + "@localhost");

        LOGGER.log(Level.INFO, "authenticated");
        return new Success(newAuthPrincipal);
    }

    public void createSessionCookie(HttpExchange exchange) {
        exchange.getResponseHeaders().add("Set-Cookie", "session=" + sessions.getValidSessionToken() + "; expires=" + Timestamp.valueOf(LocalDateTime.now().minusMinutes(30)) + "; SameSite=none; Secure");
    }
}
