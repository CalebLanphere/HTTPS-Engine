/**
 * Opens and runs an HTTPServlet
 *
 * This servlet establishes classes based off Endpoints.json
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved
 */

package com.CreativityStudios.HTTP;

import com.CreativityStudios.File.FileManager;
import com.CreativityStudios.JSON.JSONReader;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * TODO refactor
     * @return HashMap<String, String> A map with the endpoint as key and the class of the HttpHandler as the value
     * @throws IOException If a file access operation fails or the file is not readable from its stored location
     */
    private static HashMap<String, String> getEndpointsFromFile() throws IOException{
        String endpointFileLocation = "src/main/resources/HTTPEndpoints/Endpoints.json";
        HashMap<String, String> endpointMappings = new HashMap<String, String>();
        String[] endpoints;
        String[] handlers;

        if(!FileManager.doesFileExist(endpointFileLocation)) {
            LOGGER.log(Level.SEVERE, "Endpoints.json does not exist at directory: "
                    + FileManager.getAbsolutePath(endpointFileLocation));
            throw new FileNotFoundException();
            // TODO when file is not found, check if application is on first startup or if other files are present that imply previous usage
        }
        if(!FileManager.isReadable(endpointFileLocation)) {
            LOGGER.log(Level.SEVERE, "Endpoints.json cannot be read; Is the file read protected?");
            throw new IOException();
        }

        com.CreativityStudios.File.FileReader reader =
                new com.CreativityStudios.File.FileReader(endpointFileLocation);
        JSONReader.jsonStringToJsonArray(reader.readFileToString());

        endpoints = JSONReader.parseJsonArrayValueAsStringArray( "endpoint");
        handlers = JSONReader.parseJsonArrayValueAsStringArray("handlerClass");

        for(int i = 0; i < endpoints.length; i++) {
            endpointMappings.put(endpoints[i], handlers[i]);
        }

        return endpointMappings;
    }

    /**
     * Starts the HTTP servlet
     * First, the HTTP servlet is created, binding it to the local address at port 8080, with the
     * server devices backlog value used
     * Second, all user-created endpoints are loaded from the Endpoints.json file
     * Finally, the server is started
     * @throws IOException If reading getEndpointsFromFile() method has an exception
     * @throws ClassNotFoundException If the HTTPHandler class requested cannot be found
     * @throws NoSuchMethodException If the HTTPHandler has no such method to be called for getConstructor()
     * @throws InvocationTargetException If the HttpHandler constructor throws an exception
     * @throws InstantiationException If the newInstance() method being called cannot be called on the class that is attempted to be created
     * @throws IllegalAccessException If the newInstance() method being called does not have permission to create the new instance
     */
    public void start() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);

        for (Map.Entry<String, String> entry : getEndpointsFromFile().entrySet()) {
            String endpoint = entry.getKey();
            String handlerClass = entry.getValue();
            httpServer.createContext(endpoint, (HttpHandler) Class.forName(handlerClass).getConstructor().newInstance());
        }

        httpServer.start();
    }
}
