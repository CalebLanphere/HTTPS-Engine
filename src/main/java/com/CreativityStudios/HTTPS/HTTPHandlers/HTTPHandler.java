package com.CreativityStudios.HTTPS.HTTPHandlers;

import com.CreativityStudios.Database.DatabaseConnector;
import com.CreativityStudios.Exceptions.IncorrectEndpointException;
import com.CreativityStudios.Exceptions.NotAcceptedQueryException;
import com.CreativityStudios.HTTPS.HTTPRequest;
import com.CreativityStudios.HTTPS.HTTPResponse;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPStatus;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPHandler extends BaseHTTPHandler{
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // TODO abstract away error handling for incorrect URIs or improper URIs
    @Override
    public void getMapping(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        Connection conn = DatabaseConnector.getReaderConnection();
        ResultSet dbresp = conn.createStatement().executeQuery("SELECT * FROM test;");
        String resp = String.valueOf(dbresp);
        try {
            super.isURIPathExact(request.getURIPath());
        } catch(IncorrectEndpointException e) {
            response.sendResponseHeaders(HTTPStatus.NOT_FOUND);
            response.close();
        }
        if(request.getPrincipal() != null) {
            LOGGER.log(Level.INFO, ENDPOINT_URI);
            LOGGER.log(Level.INFO, resp);
            response.sendResponseHeaders(HTTPStatus.OK);
            response.addToResponseBody(resp);
            response.close();
        } else {
            LOGGER.log(Level.INFO, ENDPOINT_URI);
            LOGGER.log(Level.INFO, resp);
            response.addToResponseBody(resp);
            response.sendResponseHeaders(HTTPStatus.OK);
            response.close();
        }
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
