/**
 * BaseHTTPHandler class
 *
 * This class creates the abstraction layer for handling all HTTP responses
 *
 * <p>
 * All HTTP mapping classes can be overriden depending on the requirements of the endpoint,
 * otherwise it will fall back to returning the HTTP status 501 Not Implemented.
 *
 * The ENDPOINT_URI value must be overriden and denote the endpoint that the class is created to
 * handle, otherwise it defaults to "/test", which could create errors. This should occur in the
 * constructor of the implementing class
 * </p>
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved
 */

package com.CreativityStudios.HTTPS.HTTPHandlers;

import com.CreativityStudios.Exceptions.IncorrectEndpointException;
import com.CreativityStudios.Exceptions.NotAcceptedQueryException;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPMethods;
import com.CreativityStudios.HTTPS.HTTPResponse;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPStatus;
import com.CreativityStudios.HTTPS.HTTPRequest;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BaseHTTPHandler implements HttpHandler {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    protected String ENDPOINT_URI = "/test";

    public void setEndpointURI(String endpoint) {
        ENDPOINT_URI = endpoint;
    }

    /**
     * Handles the incoming HTTP requests and directs them to their associated HTTP method mapped methods
     * @param exchange the exchange containing the request from the
     *                 client and used to send the response
     * @throws IOException if the connection is interrupted
     */
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HTTPRequest request = new HTTPRequest(exchange);
        HTTPResponse response = new HTTPResponse(exchange);

        try {
//            try {
//                isURIPathContainingQueryMatch(request.getURIPath(), request);
//            } catch (IncorrectEndpointException e) {
//
//            }

            switch (request.getRequestMethod()) {
                case HTTPMethods.GET:
                    get(request, response);
                    break;
                case HTTPMethods.POST:
                    post(request, response);
                    break;
                case HTTPMethods.PUT:
                    put(request, response);
                    break;
                case HTTPMethods.DELETE:
                    delete(request, response);
                    break;
                case HTTPMethods.OPTIONS:
                    options(request, response);
                    break;
                case HTTPMethods.TRACE:
                    trace(request, response);
                    break;
                case HTTPMethods.PATCH:
                    patch(request, response);
                    break;
                case HTTPMethods.HEAD:
                    head(request, response);
                    break;
                case HTTPMethods.CONNECT:
                    connect(request, response);
                    break;
                default:
                    defaultMapping(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new IOException(e.getMessage(), e.getCause());
        }
        response.close();
    }

    /**
     * The mapping responsible for handling all GET requests to this endpoint
     * Used for getting contents from the server
     *
     * @param request HTTPRequest that initiated this method
     * @param response HTTPResponse that will be returned
     * @throws IOException If connection is interrupted
     */
    protected void get(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        defaultMapping(request, response);
    }

    /**
     * The mapping responsible for handling all POST requests to this endpoint
     * Used for putting entire entries into the server
     *
     * @param request HTTPRequest that initiated this method
     * @param response HTTPResponse that will be returned
     * @throws IOException If connection is interrupted
     */
    protected void post(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        defaultMapping(request, response);
    }

    /**
     * The mapping responsible for handling all PUT requests to this endpoint
     * Used for putting entire entries into the server
     *
     * @param request HTTPRequest that initiated this method
     * @param response HTTPResponse that will be returned
     * @throws IOException If connection is interrupted
     */
    protected void put(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        defaultMapping(request, response);
    }

    /**
     * The mapping responsible for handling all DELETE requests to this endpoint
     * Used for deleting entire entries into the server
     *
     * @param request HTTPRequest that initiated this method
     * @param response HTTPResponse that will be returned
     * @throws IOException If connection is interrupted
     */
    protected void delete(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        defaultMapping(request, response);
    }

    /**
     * The mapping responsible for handling all OPTIONS requests to this endpoint
     * Used for getting all the endpoint options on a given endpoint
     *
     * @param request HTTPRequest that initiated this method
     * @param response HTTPResponse that will be returned
     * @throws IOException If connection is interrupted
     */
    protected void options(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        defaultMapping(request, response);
    }

    /**
     * The mapping responsible for handling all TRACE requests to this endpoint
     *
     * @param request HTTPRequest that initiated this method
     * @param response HTTPResponse that will be returned
     * @throws IOException If connection is interrupted
     */
    protected void trace(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        defaultMapping(request, response);
    }

    /**
     * The mapping responsible for handling all PATCH requests to this endpoint
     * Used for updating parts of entries on the server
     *
     * @param request HTTPRequest that initiated this method
     * @param response HTTPResponse that will be returned
     * @throws IOException If connection is interrupted
     */
    protected void patch(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        defaultMapping(request, response);
    }

    /**
     * The mapping responsible for handling all HEAD requests to this endpoint
     * Used for getting the same response from the GET method, but without the body contents
     *
     * @param request HTTPRequest that initiated this method
     * @param response HTTPResponse that will be returned
     * @throws IOException If connection is interrupted
     */
    protected void head(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        defaultMapping(request, response);
    }

    /**
     * The mapping responsible for handling all CONNECT requests to this endpoint
     *
     * @param request HTTPRequest that initiated this method
     * @param response HTTPResponse that will be returned
     * @throws IOException If connection is interrupted
     */
    protected void connect(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        defaultMapping(request, response);
    }

    /**
     * The mapping responsible for handling all unknown or non-overriden
     * HTTP method requests to this endpoint
     *
     * @param request HTTPRequest that initiated this method
     * @param response HTTPResponse that will be returned
     * @throws IOException If connection is interrupted
     */
    protected void defaultMapping(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        try {
            isURIPathExact(ENDPOINT_URI);
        } catch(IncorrectEndpointException e) {
            response.sendResponseHeaders(HTTPStatus.NOT_FOUND);
            response.close();
        }
        response.sendResponseHeaders(HTTPStatus.NOT_IMPLEMENTED);
        response.close();
    }

    /**
     * Logs the incorrect URI to the console
     *
     * @param request request that initiated a request at an incorrect URI
     */
    protected void logWrongURI(HTTPRequest request) {
        LOGGER.log(Level.INFO, "No mapping at URI: " + request.getURIPath());
    }

    /**
     * Checks if the URI from the request is equal to the ENDPOINT_URI string
     * @param uri String request URI provided
     * @return boolean if the URI exactly matches
     */
    protected void isURIPathExact(String uri) throws IncorrectEndpointException {
        if(!uri.equals(ENDPOINT_URI)) {
            throw new IncorrectEndpointException();
        }
    }
    /**
     * Checks if the URI from the request contains the ENDPOINT_URI, and contains
     * all queries expected by the method
     *
     * @param uri String request URI provided
     * @param mapOfQueries HashMap<String, Object> map containing all URI queries provided
     * @return boolean if the URI contains the endpoint and all expected queries
     */
    protected void isURIPathContainingQueryMatch(String uri, HashMap<String, Object> mapOfQueries) throws NotAcceptedQueryException, IncorrectEndpointException {
        if(!uri.contains(ENDPOINT_URI)) {
            throw new IncorrectEndpointException();
        }

        for(String key : mapOfQueries.keySet()) {
            if(!uri.contains(key)) {
                throw new NotAcceptedQueryException();
            }
        }

    }

}
