package com.CreativityStudios.HTTP;

import com.CreativityStudios.File.FileCreator;
import com.CreativityStudios.JSON.JSONReader;
import com.sun.net.httpserver.Filter;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    // int AMOUNT_OF_CONTEXT_URLS is Generated based off number of Endpoints.json entries
    private static int AMOUNT_OF_CONTEXT_URLS;
    private static String[] endpoints;
    // String[] handlers is used to identify which HTTPHandler to use that is generated with each endpoint
    private static String[] handlers;

    /**
     * TODO refactor
     * @return
     * @throws IOException
     */
    private static void setEndpointsFromFile() throws IOException{
        String endpointFileLocation = "src/main/resources/HTTPEndpoints/Endpoints.json";

        if(!FileCreator.doesFileExist(endpointFileLocation)) {
            LOGGER.log(Level.SEVERE, "Endpoints.json does not exist at directory: "
                    + FileCreator.getAbsolutePath(endpointFileLocation));
            throw new FileNotFoundException();
        }
        if(!FileCreator.isReadable(endpointFileLocation)) {
            LOGGER.log(Level.SEVERE, "Endpoints.json cannot be read; Is the file read protected?");
            throw new IOException();
        }

        com.CreativityStudios.File.FileReader reader =
                new com.CreativityStudios.File.FileReader(endpointFileLocation);
        JSONReader jsonReader = new JSONReader();
        jsonReader.jsonStringToJsonArray(reader.readFileToString());

        endpoints = jsonReader.parseJsonArrayValueAsStringArray( "endpoint");
        handlers = jsonReader.parseJsonArrayValueAsStringArray("handlerClass");
        AMOUNT_OF_CONTEXT_URLS = endpoints.length;
    }

    /**
     * Starts the HTTP servlet
     * @throws IOException
     * @throws ClassNotFoundException If the HTTPHandler cannot be found
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public void start() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);
        setEndpointsFromFile();
        for(int i = 0; i < AMOUNT_OF_CONTEXT_URLS; i++) {
            httpServer.createContext(endpoints[i], (HttpHandler) Class.forName("com.CreativityStudios.HTTP.HTTPHandlers.TestHttpHandler").getConstructor().newInstance());
        }
        httpServer.start();
    }
}
