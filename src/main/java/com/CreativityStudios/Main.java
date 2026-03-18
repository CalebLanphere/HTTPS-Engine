package com.CreativityStudios;

import com.CreativityStudios.File.FileCreator;
import com.CreativityStudios.HTTP.HTTPServlet;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        try {
            HTTPServlet servlet = new HTTPServlet();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error on starting HTTP Servlet: " + e.getMessage());
        }
        LOGGER.log(Level.INFO, "Service started");
    }
}