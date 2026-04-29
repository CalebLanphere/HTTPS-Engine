package com.CreativityStudios.HTTPS;

import com.CreativityStudios.HTTPS.Authentication.AuthenticationResult;
import com.CreativityStudios.HTTPS.Authentication.UserSession;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionManager {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final HashMap<String, UserSession> sessions = new HashMap<>();

    public boolean isSessionValid(String sessionUUID) {
        if(sessionUUID == null) {
            return false;
        }

        return sessions.containsKey(sessionUUID);
    }

    public UserSession getSession(String sessionUUID) {
        return sessions.get(sessionUUID);
    }

    public UserSession add(String sessionUUID, AuthenticationResult authResult) {
        sessions.put(sessionUUID, new UserSession(authResult, createSessionExpiryTimestamp()));
        return sessions.get(sessionUUID);
    }

    public String getValidSessionToken() {
        return UUID.randomUUID().toString();
    }

    private Timestamp createSessionExpiryTimestamp() {
        return Timestamp.valueOf(LocalDateTime.now().plusMinutes(30));
    }
}
