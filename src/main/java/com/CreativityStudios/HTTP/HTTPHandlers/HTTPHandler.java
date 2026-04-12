package com.CreativityStudios.HTTP.HTTPHandlers;

import com.CreativityStudios.Exceptions.IncorrectEndpointException;
import com.CreativityStudios.Exceptions.NotAcceptedQueryException;
import com.CreativityStudios.HTTP.HTTPRequest;
import com.CreativityStudios.HTTP.HTTPResponse;
import com.CreativityStudios.HTTP.HTTPStatus;

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

public class HTTPHandler extends BaseHTTPHandler{
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public HTTPHandler() {
        ENDPOINT_URI = "/health";
    }

    // TODO abstract away error handling for incorrect URIs or improper URIs
    @Override
    public void getMapping(HTTPRequest request, HTTPResponse response) throws IOException {
        try {
            super.isURIPathExact(request.getURIPath());
        } catch(IncorrectEndpointException e) {
            response.sendResponseHeaders(HTTPStatus.NOT_FOUND);
            response.close();
        }
        response.addHeaderEntry("Content-Type", "text/html");
        response.addHeaderEntry("Access-control-allow-origin", "*");
        response.sendResponseHeaders(HTTPStatus.OK);
        response.addToResponseBody("<div id='elementsWanted'><button>test</button><button>test 2</button></div>");
        response.close();
    }

    @Override
    protected void putMapping(HTTPRequest request, HTTPResponse response) throws IOException {
        HashMap<String, Object> map = new HashMap<>();
        map.put("query", null);
        map.put("id", null);

        try {
            super.isURIPathContainingQueryMatch(request.getURIPath(), map);
        } catch(NotAcceptedQueryException | IncorrectEndpointException e) {
            if(e.getClass().equals(NotAcceptedQueryException.class)) {
                response.sendResponseHeaders(HTTPStatus.BAD_REQUEST);
                response.close();
            }
            response.sendResponseHeaders(HTTPStatus.NOT_FOUND);
            response.close();
        }

        response.addHeaderEntry("Content-Type", "text/html");
        response.addHeaderEntry("Access-control-allow-origin", "*");
        response.sendResponseHeaders(HTTPStatus.CREATED);
        response.close();
    }
}
