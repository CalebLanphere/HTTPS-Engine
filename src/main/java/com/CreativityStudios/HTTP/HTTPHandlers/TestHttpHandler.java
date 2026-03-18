package com.CreativityStudios.HTTP.HTTPHandlers;

import com.CreativityStudios.HTTP.HTTPMethods;
import com.CreativityStudios.HTTP.HTTPStatus;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestHttpHandler implements HttpHandler {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private InputStream reader;
    private OutputStream writer;

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        LOGGER.log(Level.INFO, "Exchange at /test");
        reader = exchange.getRequestBody();
        writer = exchange.getResponseBody();

        switch(exchange.getRequestMethod()) {
            case HTTPMethods.GET:
                String response = "<div id='elementsWanted'><button>test</button><button>test 2</button></div>";
                exchange.getResponseHeaders().add("Content-type", "text/html");
                exchange.getResponseHeaders().add("Access-control-allow-origin", "*");
                exchange.sendResponseHeaders(HTTPStatus.OK, response.length());
                writer.write(response.getBytes());
                writer.close();
                break;
            case HTTPMethods.PUT:
                exchange.sendResponseHeaders(HTTPStatus.NOT_IMPLEMENTED, -1);
                reader.close();
                writer.close();
            default:
                exchange.sendResponseHeaders(HTTPStatus.NOT_IMPLEMENTED, -1);
                reader.close();
                writer.close();
        }
    }
}
