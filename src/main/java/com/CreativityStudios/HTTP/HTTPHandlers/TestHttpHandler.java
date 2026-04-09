package com.CreativityStudios.HTTP.HTTPHandlers;

import com.CreativityStudios.HTTP.HTTPMethods;
import com.CreativityStudios.HTTP.HTTPResponse;
import com.CreativityStudios.HTTP.HTTPStatus;
import com.CreativityStudios.HTTP.HTTPRequest;
import com.CreativityStudios.JSON.JSONWriter;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 TODO ABSTRACT FURTHER DOWN IF POSSIBLE
 */
public class TestHttpHandler implements HttpHandler {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private boolean isURLPathExact(String url) {
        if(!url.equals("/test")) {
            return false;
        }
        return true;
    }

    private boolean isURLPathContainingQueryMatch(String url) {
        if(!url.contains("/test?query=")) {
            return false;
        }
        return true;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HTTPRequest request = new HTTPRequest(exchange);
        HTTPResponse response = new HTTPResponse(exchange);
        LOGGER.log(Level.INFO, "Exchange at /test");

        switch(request.getRequestMethod()) {
            case HTTPMethods.GET:
                if(!isURLPathExact(request.getURIPath())) {
                    LOGGER.log(Level.INFO, "Wrong URL at /test");
                    response.sendResponseHeaders(HTTPStatus.BAD_REQUEST);
                    break;
                }
                response.addHeaderEntry("Content-Type", "text/html");
                response.addHeaderEntry("Access-control-allow-origin", "*");
                response.sendResponseHeaders(HTTPStatus.OK);
                response.addToResponseBody("<div id='elementsWanted'><button>test</button><button>test 2</button></div>");
                break;
            case HTTPMethods.PUT:
                if(!isURLPathContainingQueryMatch(request.getURIPath())) {
                    LOGGER.log(Level.INFO, "Wrong URL at /test");
                    exchange.sendResponseHeaders(HTTPStatus.BAD_REQUEST, -1);
                    break;
                }
                LOGGER.log(Level.INFO, request.getRequestBodyAsString());
                response.sendResponseHeaders(HTTPStatus.METHOD_NOT_ALLOWED);
                break;
            case HTTPMethods.PATCH:
                if(!isURLPathContainingQueryMatch(request.getURIPath())) {
                    LOGGER.log(Level.INFO, "Wrong URL at /test");
                    response.sendResponseHeaders(HTTPStatus.BAD_REQUEST);
                    break;
                }
                HashMap<String, String> map = new HashMap<String, String>();
                map.put("test", "value");
                response.sendResponseHeaders(HTTPStatus.OK);
                response.addToResponseBody(JSONWriter.createJsonTestObject(map));
                break;
            default:
                response.sendResponseHeaders(HTTPStatus.NOT_IMPLEMENTED);
                break;
        }
        response.close();
    }
}
