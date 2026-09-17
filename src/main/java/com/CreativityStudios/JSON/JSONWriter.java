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
}
