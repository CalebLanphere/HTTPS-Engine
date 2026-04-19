package com.CreativityStudios.HTTPS;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SessionManager {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final HashMap<String, Timestamp> sessions = new HashMap<>();

    public boolean isSessionValid(String session) {
        if(session == null) {
            return false;
        }

        return sessions.containsKey(session);
    }

    public void add(String session) {
        LOGGER.log(Level.INFO, "added session #" + session.split("=")[1]);
        sessions.put(session.split("=")[1], Timestamp.valueOf(LocalDateTime.now().minusMinutes(30)));
    }

    public String getValidSessionToken() {
        return UUID.randomUUID().toString();
    }
}
