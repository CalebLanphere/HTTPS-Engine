package com.CreativityStudios.HTTP.HTTPHandlers;

import com.CreativityStudios.HTTP.HTTPMethods;
import com.CreativityStudios.HTTP.HTTPStatus;
import com.CreativityStudios.HTTP.HttpRequest;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestHttpHandler implements HttpHandler {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private InputStream reader;
    private OutputStream writer;

    private boolean isURLPathExact(String url) {
        if(!url.equals("/test/")) {
            return false;
        }
        return true;
    }

    private void closeReaderAndWriter() throws IOException {
        reader.close();
        writer.close();
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HttpRequest request = new HttpRequest(exchange);
        if(!isURLPathExact(request.getURIPath())) {
            LOGGER.log(Level.INFO, "Wrong URL at /test");
            exchange.sendResponseHeaders(HTTPStatus.NOT_IMPLEMENTED, -1);
            closeReaderAndWriter();
        }
        LOGGER.log(Level.INFO, "Exchange at /test");
        reader = exchange.getRequestBody();
        writer = exchange.getResponseBody();

        switch(request.getRequestMethod()) {
            case HTTPMethods.GET:
                String response = "<div id='elementsWanted'><button>test</button><button>test 2</button></div>";
                exchange.getResponseHeaders().add("Content-type", "text/html");
                exchange.getResponseHeaders().add("Access-control-allow-origin", "*");
                exchange.sendResponseHeaders(HTTPStatus.OK, response.length());
                writer.write(response.getBytes());
                break;
            case HTTPMethods.PUT:
                exchange.sendResponseHeaders(HTTPStatus.NOT_IMPLEMENTED, -1);
            default:
                exchange.sendResponseHeaders(HTTPStatus.NOT_IMPLEMENTED, -1);
        }
        closeReaderAndWriter();
    }
}
