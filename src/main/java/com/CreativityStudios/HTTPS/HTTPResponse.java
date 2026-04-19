/**
 * HTTPResponse class
 *
 * An abstraction of the HttpExchange classes actions regarded to preparing and sending the HTTP
 * server response
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.HTTPS;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

public class HTTPResponse {
    private final HttpExchange exchange;
    private final OutputStream writer;
    private final Headers responseHeaders;
    private long messageLength = 0;

    /**
     * Adds the newly added message in the write buffer to the messageLength tracker
     *
     * @param lengthOfMessage message length to add to total message length
     */
    private void addToMessageLength(long lengthOfMessage) {
        messageLength = messageLength + lengthOfMessage;
    }

    /**
     * Creates an HTTPReponse object with the supplied exchange
     * @param exchange HttpExchange created for client/server communication for a given request
     */
    public HTTPResponse(HttpExchange exchange) {
        this.exchange = exchange;
        writer = exchange.getResponseBody();
        responseHeaders = exchange.getResponseHeaders();
    }

    /**
     * Sends the requesting client the HTTP headers, which signals the end of the HTTP communication
     * for a given request. This should be the last call made in handling an HTTP request
     *
     * @param status HTTP status to send to the requesting client
     * @throws IOException If the communication stream is interrupted for any reason
     */
    public void sendResponseHeaders(int status) throws IOException {
        this.exchange.sendResponseHeaders(status, messageLength);
    }

    /**
     * Adds an entry to the response headers
     *
     * @param key The identifier of the header entry
     * @param value The value of the header entry
     */
    public void addHeaderEntry(String key, String value) {
        responseHeaders.add(key, value);
    }

    /**
     * Writes an object into the response body
     *
     * @param object Object to convert to Byte[] and write to request body
     * @throws IOException If the communication stream is interrupted for any reason
     */
    public void addToResponseBody(Object object) throws IOException {
        byte[] responseBody = String.valueOf(object).getBytes();
        addToMessageLength(responseBody.length);

        writer.write(responseBody);
    }

    /**
     * Closes the writer stream associated with the response body
     * @throws IOException If the communication stream is interrupted for any reason
     */
    public void close() throws IOException {
        writer.close();
    }
}
