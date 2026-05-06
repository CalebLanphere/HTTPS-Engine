package com.CreativityStudios.HTTPS.HTTPHandlers;

import com.CreativityStudios.Exceptions.IncorrectEndpointException;
import com.CreativityStudios.Exceptions.NotAcceptedQueryException;
import com.CreativityStudios.File.FileReader;
import com.CreativityStudios.GCOP.EOCPElement;
import com.CreativityStudios.GCOP.EOCPElementBuilder;
import com.CreativityStudios.HTTPS.HTTPRequest;
import com.CreativityStudios.HTTPS.HTTPResponse;
import com.CreativityStudios.HTTPS.HTTPStatus;
import com.CreativityStudios.JSON.JSONWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Logger;

public class HTTPDefaultGetHandler extends BaseHTTPFileHandler{
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // TODO abstract away error handling for incorrect URIs or improper URIs
    // TODO abstract further to hide OPTIONS protocol
    @Override
    public void getMapping(HTTPRequest request, HTTPResponse response) throws IOException {
        try {
            super.isURIPathExact(request.getURIPath());
        } catch(IncorrectEndpointException e) {
            response.sendResponseHeaders(HTTPStatus.NOT_FOUND);
            response.close();
        }
        response.addHeaderEntry("Content-Type", FILE_TYPE);
        response.addHeaderEntry("Access-Control-Allow-Origin", "http://localhost:63343");
        response.addHeaderEntry("Access-Control-Allow-Credentials", "true");

        response.sendResponseHeaders(HTTPStatus.OK);
        response.addToResponseBody(new FileReader(FILE_LOCATION).readFileToByteArray());
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

        response.addHeaderEntry("Access-Control-Allow-Origin", "http://localhost:63343");
        response.addHeaderEntry("Access-Control-Allow-Credentials", "true");
        response.addHeaderEntry("Content-Type", "text/html");
        response.sendResponseHeaders(HTTPStatus.CREATED);
        response.close();
    }

    @Override
    protected void optionsMapping(HTTPRequest request, HTTPResponse response) throws IOException {
        response.addHeaderEntry("Access-Control-Allow-Origin", "http://localhost:63343");
        response.addHeaderEntry("Access-Control-Allow-Credentials", "true");
        response.addHeaderEntry("Access-Control-Allow-Methods", "GET, PUT, OPTIONS");
        response.addHeaderEntry("Access-Control-Allow-Headers", "Authorization, Cookies");
        response.sendResponseHeaders(HTTPStatus.NO_CONTENT);
        response.close();
    }
}
