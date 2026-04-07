package com.CreativityStudios.HTTP;

import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

public class HttpRequest {
    private final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private final HashMap<String, String> headers = new HashMap<String, String>();
    private final URI uri;
    private final String protocol;
    private final String webAddress;
    private final int port;
    private final String requestMethod;
    private final byte[] bodyContents;

    public HttpRequest(HttpExchange exchange) throws IOException {
        exchange.getRequestHeaders().forEach((key, value) -> headers.put(key, Arrays.toString(value.toArray())));
        uri = exchange.getRequestURI();
        protocol = exchange.getProtocol();
        webAddress = exchange.getRemoteAddress().getHostName();
        port = exchange.getLocalAddress().getPort();
        requestMethod = exchange.getRequestMethod();
        bodyContents = exchange.getRequestBody().readAllBytes();
    }

    public String getURI(){
            return getDomainName() + ":" + getDomainPort() + getURIPath();
    }

    public String getURIPath() {
        return uri.getPath();
    }

    public String getURIAuthority() {
        return uri.getAuthority();
    }

    public String getProtocol() {
        return protocol;
    }

    private String getDomainName() {
        return webAddress;
    }

    private int getDomainPort() {
        return port;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public String getRequestBodyAsString() {
        String requestBodyString = "";
        for(byte i : bodyContents) {
            requestBodyString = requestBodyString.concat(Byte.toString(i));
        }
        return requestBodyString;
    }
}
