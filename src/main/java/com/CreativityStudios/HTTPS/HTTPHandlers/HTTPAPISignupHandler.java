package com.CreativityStudios.HTTPS.HTTPHandlers;

import com.CreativityStudios.Database.Users.UserEntry;
import com.CreativityStudios.Database.Users.UserDatabaseCalls;
import com.CreativityStudios.Exceptions.IncorrectEndpointException;
import com.CreativityStudios.HTTPS.HTTPRequest;
import com.CreativityStudios.HTTPS.HTTPResponse;
import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPStatus;
import com.CreativityStudios.JSON.JSONReader;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPAPISignupHandler extends BaseHTTPHandler{
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    // TODO abstract away error handling for incorrect URIs or improper URIs
    @Override
    public void putMapping(HTTPRequest request, HTTPResponse response) throws IOException, SQLException {
        try {
            super.isURIPathExact(request.getURIPath());
        } catch(IncorrectEndpointException e) {
            response.sendResponseHeaders(HTTPStatus.NOT_FOUND);
            response.close();
            return;
        } // TODO ABSTRACT AWAY CORS-POLICY REQUIREMENTS IN HEADERS
            UserEntry userEntryToAdd = JSONReader.parseJsonObjectAsUserEntryToCreate(request.getRequestBodyAsJSONObject());
            if(UserDatabaseCalls.ifUserEmailFromUsersWithUserEmailExists(userEntryToAdd.getEmail())) {
                response.sendResponseHeaders(HTTPStatus.BAD_REQUEST);
                response.addToResponseBody("Error: Account already tied to email");
                response.close();
            }
            if(!UserDatabaseCalls.insertUserEntryIntoUsers(userEntryToAdd)) {
                response.sendResponseHeaders(HTTPStatus.NO_CONTENT);
                response.close();
            } else {
                response.sendResponseHeaders(HTTPStatus.INTERNAL_SERVER_ERROR);
                response.close();
            }
    }

    @Override
    protected void optionsMapping(HTTPRequest request, HTTPResponse response) throws IOException {
        LOGGER.log(Level.INFO, "options triggered");
        response.addHeaderEntry("Access-Control-Allow-Origin", "http://localhost:63343");
        response.addHeaderEntry("Access-Control-Allow-Credentials", "true");
        response.addHeaderEntry("Access-Control-Allow-Methods", "PUT, OPTIONS");
        response.addHeaderEntry("Access-Control-Allow-Headers", "Authorization, Cookies");
        response.sendResponseHeaders(HTTPStatus.NO_CONTENT);
        response.close();
    }
}
