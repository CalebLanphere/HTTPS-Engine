/**
 * Project Expiry - Application Start class
 * @author Caleb Lanphere
 *
 * Last Modified 04/09/2026
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved
 *
 */

package com.CreativityStudios;

import com.CreativityStudios.Database.DatabaseConnector;
import com.CreativityStudios.HTTPS.HTTPSServlet;
import com.CreativityStudios.HTTPS.Sessions.Cookie.CookieSessionManager;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static final HTTPSServlet servlet = new HTTPSServlet();
    private static DatabaseConnector database = null;
    private static CookieSessionManager sessionManager;

    public static void main(String[] args) {
        try {
            database = new DatabaseConnector();
            sessionManager = new CookieSessionManager();

            servlet.start();
        } catch (Exception e) {
            // Logs the exception received, the class that issued it, and the message associated
            LOGGER.log(Level.SEVERE, "Error on starting HTTP Servlet: " + e.getClass() + " " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            System.exit(1);
        }
        LOGGER.log(Level.INFO, "Service started");
    }
}