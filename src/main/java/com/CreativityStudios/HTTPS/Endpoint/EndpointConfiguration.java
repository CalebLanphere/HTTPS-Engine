package com.CreativityStudios.HTTPS.Endpoint;

import com.CreativityStudios.HTTPS.HTTPHandlers.BaseHTTPFileHandler;
import com.CreativityStudios.HTTPS.HTTPHandlers.BaseHTTPHandler;
import com.sun.net.httpserver.HttpHandler;

import java.lang.reflect.InvocationTargetException;

public class EndpointConfiguration {
    private final String endpointURI;
    private final String handlerClass;
    private String fileLocation;
    private String fileType;
    private final boolean isAuthRequired;

    public EndpointConfiguration(String endpoint, String handler, boolean isAuthRequired) {
        endpointURI = endpoint;
        handlerClass = handler;
        this.isAuthRequired = isAuthRequired;
    }

    public EndpointConfiguration(String endpoint, String handler, boolean isAuthRequired, String fileLocation, String fileType) {
        endpointURI = endpoint;
        handlerClass = handler;
        this.isAuthRequired = isAuthRequired;
        this.fileLocation = fileLocation;
        this.fileType = fileType;
    }

    public String getEndpoint() {
        return endpointURI;
    }

    public HttpHandler getInstanceOfHandler() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        if(fileLocation != null) {
            return setupFileHttpHandler();
        }
        return setupHttpHandler();
    }

    private HttpHandler setupFileHttpHandler() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        BaseHTTPFileHandler handler = (BaseHTTPFileHandler) Class.forName(handlerClass).getConstructor().newInstance();
        handler.setEndpointURI(endpointURI);
        handler.setFileLocation(fileLocation);
        handler.setFileType(fileType);
        return handler;
    }

    private HttpHandler setupHttpHandler() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        BaseHTTPHandler handler = (BaseHTTPHandler) Class.forName(handlerClass).getConstructor().newInstance();
        handler.setEndpointURI(endpointURI);
        return handler;
    }

    public String getHandlerClassName() {
        return handlerClass;
    }

    public boolean isAuthRequired() {
        return isAuthRequired;
    }

    public String toString() {
        return "Endpoint:" + new String("\nEndpoint URI: " + endpointURI + "\nHandler Class Name: " + handlerClass + "\nRequires Authentication: " + isAuthRequired).indent(2);
    }

}
