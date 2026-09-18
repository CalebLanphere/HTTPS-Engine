/**
 * Cookie Session Manager
 *
 * Handles session tokens by sending the requestee a Cookie, which contains a UUID for authentication and state
 * This UUID is mapped to UserSession object, which contains the authenticated users email, domain, and
 */

package com.CreativityStudios.HTTPS.Sessions.Cookie;

import com.CreativityStudios.HTTPS.Authentication.AuthenticationResult;
import com.CreativityStudios.HTTPS.Sessions.Token;
import com.CreativityStudios.HTTPS.Sessions.UserSession;
import com.sun.net.httpserver.HttpExchange;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CookieSessionManager{
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final HashMap<String, UserSession> sessions = new HashMap<>();

    public static boolean isSessionValid(Token sessionToken) {
        if(sessionToken.getToken() == null || sessions.isEmpty()) {
            return false;
        }

        return sessions.containsKey(sessionToken.getToken());
    }

    public static UserSession getSession(Token sessionToken) {
        return sessions.get(sessionToken.getToken());
    }

    public static UserSession add(Token sessionToken, AuthenticationResult authResult) {
        // TODO MERGE UserSession class INTO UserEntry class
        sessions.put(sessionToken.getToken(), new UserSession(authResult, createSessionExpiryTimestamp()));
        return sessions.get(sessionToken.getToken());
    }

    public static CookieToken getValidSessionToken() {
        return new CookieToken(UUID.randomUUID().toString());
    }

    public static void createSessionToken(HttpExchange exchange, AuthenticationResult result) {
        CookieToken newSession = getValidSessionToken();
        UserSession addedSession = add(newSession, result);
        LOGGER.log(Level.INFO, "added session to cache");
        exchange.getResponseHeaders().add("Set-Cookie", "session=" + newSession.getToken() + "; expires=" + addedSession.getExpiryTime() + "; SameSite=none; Secure" + "; HttpOnly");
    }

    private static Timestamp createSessionExpiryTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now().plusMinutes(30));
    }
}
