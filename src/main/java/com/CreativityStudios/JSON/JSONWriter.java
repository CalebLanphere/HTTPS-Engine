/**
 * JSONWriter class
 *
 * Helps generate JSON Objects and Arrays for various classes and objects
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.JSON;

import com.CreativityStudios.Database.Users.UserPermissions;
import jakarta.json.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class JSONWriter {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static JsonObject createUserPermissionsJsonObject(UserPermissions permissions) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("permissions", "all");

        return builder.build();
    }

    public static JsonObject createBearerTokenJsonObject(String token) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        builder.add("Token", token);

        return builder.build();
    }

    /**
     * Creates a Json formatted dbaccess file that is read by DatabaseConnector to setup database communications
     * @return JsonObject object created
     */
    public static JsonObject createDefaultDbAccessConfigJsonObject() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

        JsonObject dbCredentialArray = builder.add("CredentialType", "null")
                .add("Username", "null")
                .add("Password", "null")
                .build();

        arrBuilder.add(dbCredentialArray);
        arrBuilder.add(dbCredentialArray);
        arrBuilder.add(dbCredentialArray);
        arrBuilder.add(dbCredentialArray);

        builder.add("DatabaseURL", "null");
        builder.add("databaseCredentials", arrBuilder.build());

        return builder.build();
    }

    public static JsonArray createDefaultEndpointsJsonObject() {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();

         arrBuilder.add(builder.add("endpoint", "/welcome")
                .add("handlerClass", "com.CreativityStudios.HTTPS.HTTPHandlers.HTTPDefaultGetHandler")
                .add("requireAuthentication", "false")
                .build());

        return arrBuilder.build();
    }
}
