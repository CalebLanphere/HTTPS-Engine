package com.CreativityStudios.HTTPS;

import com.sun.net.httpserver.HttpHandler;

import java.lang.reflect.InvocationTargetException;

public class EndpointConfiguration {
    private String endpointURI;
    private String handlerClass;
    private boolean isAuthRequired;

    public EndpointConfiguration(String endpoint, String handler, boolean isAuthRequired) {
        endpointURI = endpoint;
        handlerClass = handler;
        this.isAuthRequired = isAuthRequired;
    }

    public String getEndpoint() {
        return endpointURI;
    }

    public HttpHandler getInstanceOfHandler() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        return (HttpHandler) Class.forName(handlerClass).getConstructor().newInstance();
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
