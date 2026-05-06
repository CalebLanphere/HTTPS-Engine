package com.CreativityStudios.HTTPS.HTTPHandlers;

import com.CreativityStudios.Exceptions.IncorrectEndpointException;
import com.CreativityStudios.HTTPS.HTTPRequest;
import com.CreativityStudios.HTTPS.HTTPResponse;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPStatus;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPAPILoginHandler extends BaseHTTPHandler{
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // TODO abstract away error handling for incorrect URIs or improper URIs
    @Override
    public void postMapping(HTTPRequest request, HTTPResponse response) throws IOException {
        try {
            super.isURIPathExact(request.getURIPath());
        } catch(IncorrectEndpointException e) {
            response.sendResponseHeaders(HTTPStatus.NOT_FOUND);
            response.close();
            return;
        } // TODO ABSTRACT AWAY CORS-POLICY REQUIREMENTS IN HEADERS
        if(request.getPrincipal() != null) {
            response.addHeaderEntry("Access-Control-Allow-Origin", "http://localhost:63343");
            response.addHeaderEntry("Access-Control-Allow-Credentials", "true");
            response.sendResponseHeaders(HTTPStatus.NO_CONTENT);
            response.close();
        }
    }

    @Override
    protected void optionsMapping(HTTPRequest request, HTTPResponse response) throws IOException {
        LOGGER.log(Level.INFO, "options triggered");
        response.addHeaderEntry("Access-Control-Allow-Origin", "http://localhost:63343");
        response.addHeaderEntry("Access-Control-Allow-Credentials", "true");
        response.addHeaderEntry("Access-Control-Allow-Methods", "POST, OPTIONS");
        response.addHeaderEntry("Access-Control-Allow-Headers", "Authorization, Cookies");
        response.sendResponseHeaders(HTTPStatus.NO_CONTENT);
        response.close();
    }
}
