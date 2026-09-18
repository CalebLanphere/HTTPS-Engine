package com.CreativityStudios.HTTPS.HTTPHandlers;

import com.CreativityStudios.Exceptions.IncorrectEndpointException;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPStatus;
import com.CreativityStudios.HTTPS.HTTPRequest;
import com.CreativityStudios.HTTPS.HTTPResponse;

import java.io.IOException;
import java.sql.SQLException;

public class HTTPHandlerTest extends BaseHTTPHandler{
    @Override
    protected void get(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        try {
            super.isURIPathExact(request.getURIPath());
        } catch (IncorrectEndpointException e) {
            defaultMapping(request, response);
        }
        response.sendResponseHeaders(HTTPStatus.NO_CONTENT);
        response.close();
    }
}
