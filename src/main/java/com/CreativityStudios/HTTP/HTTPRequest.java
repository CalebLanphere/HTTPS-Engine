/**
 * HTTPRequest class
 *
 * An abstraction of the HttpExchange classes actions regarded to reading the HTTP client request
 * that the server received
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.HTTP;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPRequest {
    private final Headers headers;
    private final URI uri;
    private final String protocol;
    private final String webAddress;
    private final int port;
    private final String requestMethod;
    private final byte[] bodyContents;

    /**
     * Creates an HTTPRequest object with the supplied exchange
     *
     * @param exchange HttpExchange associated with the current HTTP request
     * @throws IOException If the communication stream is interrupted for any reason
     */
    public HTTPRequest(HttpExchange exchange) throws IOException {
        headers = exchange.getRequestHeaders();
        uri = exchange.getRequestURI();
        protocol = exchange.getProtocol();
        webAddress = exchange.getRemoteAddress().getHostName();
        port = exchange.getLocalAddress().getPort();
        requestMethod = exchange.getRequestMethod();
        bodyContents = exchange.getRequestBody().readAllBytes();
    }

    /**
     * Gets the entire URL that the client used to connect to this endpoint
     * @return String entire URL
     */
    public String getURI(){
        if(getURIQuery() != null) {
            return getDomainName() + ":" + getDomainPort() + getURIPath() + "?" + getURIQuery();
        } else {
            return getDomainName() + ":" + getDomainPort() + getURIPath();
        }
    }

    /**
     * Gets the URI Path, meaning, the URL path without the IP address or the port
     * ex. 1)
     *      Full URL: "http://localhost:8080/test"
     *      Method returns: "/test"
     ex. 2)
     *      Full URL: "http://localhost:8080/health?page=main"
     *      Method returns: "/health?page=main"
     * @return String URI path from the request sent by the client
     */
    public String getURIPath() {
        // If a URI query exists, return the path with the proper query appended
        if(getURIQuery() != null) {
            return uri.getPath() + "?" + getURIQuery();
        } else {
            return uri.getPath();
        }
    }

    /**
     * Gets all queries associated with the request
     * TODO implement HTTPQueryReader to return HashMap of queries
     *
     * @return String queries associated with the request
     */
    public String getURIQuery() {
        return uri.getQuery();
    }

//    public String getURIAuthority() {
//        return uri.getAuthority();
//    }
//
//    public String getProtocol() {
//        return protocol;
//    }

    /**
     * Gets the full domain name associated with this server
     * examples:
     *      "localhost"
     *      "verifi.net"
     *
     * @return String domain name associated with this server
     */
    private String getDomainName() {
        return webAddress;
    }

    /**
     * Gets the port used to connect to this server
     * examples:
     *      8080
     *      80
     * @return
     */
    private int getDomainPort() {
        return port;
    }

    /**
     * Gets the HTTP method used by the requesting client
     * examples:
     *      GET
     *      PUT
     * @return String HTTP method sent by the requesting client
     */
    public String getRequestMethod() {
        return requestMethod;
    }

    /**
     * Gets the contents of the request body and transforms it to a String
     *
     * @return String representation of content from the request body
     */
    public String getRequestBodyAsString() {
        return new String(bodyContents);
    }

    /**
     * Gets the contents of the request body and transforms it to a JsonObject
     *
     * @return JsonObject representation of content from the request body
     */
    public JsonObject getRequestBodyAsJSONObject() {
        return Json.createReader(new StringReader(new String(bodyContents))).readObject();
    }

    /**
     * Gets the contents of the request body and transforms it to a JsonArray
     *
     * @return JsonArray representation of content from the request body
     */
    public JsonArray getRequestBodyAsJSONArray() {
        return Json.createReader(new StringReader(new String(bodyContents))).readArray();
    }
}
