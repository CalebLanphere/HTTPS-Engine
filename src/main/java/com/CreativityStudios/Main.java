package com.CreativityStudios;

import com.CreativityStudios.HTTP.HTTPServlet;

import com.CreativityStudios.File.FileReader;
import com.CreativityStudios.JSON.JSONReader;
import com.sun.net.httpserver.HttpHandler;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void main(String[] args) {
        try {
            HTTPServlet servlet = new HTTPServlet();
            servlet.start();
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error on starting HTTP Servlet: " + e.getClass() + " " + e.getMessage());
            System.exit(1);
        }
        LOGGER.log(Level.INFO, "Service started");
    }
}