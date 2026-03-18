package com.CreativityStudios.HTTP;

import com.CreativityStudios.HTTP.HTTPHandlers.TestHttpHandler;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jakarta.json.Json;
import jakarta.json.JsonArray;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.InetSocketAddress;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPServlet {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private static int AMOUNT_OF_CONTEXT_URLS;
    private static String[] endpoints;
    private static String[] handlers;

    private static String[] getEndpointsFromFile() throws IOException{
        String jsonString = "";
        File endpointsList = new File("src/main/resources/HTTPEndpoints/Endpoints.json");

        if(!endpointsList.exists()) {
            LOGGER.log(Level.SEVERE, "Endpoints.json does not exist at directory: "
                    + endpointsList.getAbsolutePath());
            throw new FileNotFoundException();
        }
        if(!endpointsList.canRead()) {
            LOGGER.log(Level.SEVERE, "Endpoints.json cannot be read; Is the file read protected?");
            throw new IOException();
        }

        BufferedReader endpointReader = new BufferedReader(new FileReader(endpointsList));
        while(endpointReader.ready()) {
            jsonString = jsonString.concat(endpointReader.readLine());
        }

        JsonArray endpointArray = Json.createReader(new StringReader(jsonString)).readArray();

        endpoints = new String[endpointArray.size()];
        handlers = new String[endpointArray.size()];
        AMOUNT_OF_CONTEXT_URLS = endpointArray.size();
        for(int i = 0; i < endpointArray.size(); i++) {
            String valToModify = endpointArray.getJsonObject(i).get("endpoint").toString();
            valToModify = valToModify.substring(1);
            valToModify = valToModify.substring(0, valToModify.length() - 1);
            endpoints[i] = valToModify;

            valToModify = endpointArray.getJsonObject(i).get("handlerClass").toString();
            valToModify = valToModify.substring(1);
            valToModify = valToModify.substring(0, valToModify.length() - 1);
            handlers[i] = valToModify;
        }
        return endpoints;
    }

    public HTTPServlet() throws IOException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        HttpServer httpServer = HttpServer.create();
        String[] endpoints = getEndpointsFromFile();
        for(int i = 0; i < AMOUNT_OF_CONTEXT_URLS; i++) {
            httpServer.createContext(endpoints[i], (HttpHandler) Class.forName(handlers[i]).getConstructor().newInstance());
        }
        httpServer.bind(new InetSocketAddress(8080), 0);
        httpServer.start();
    }
}
