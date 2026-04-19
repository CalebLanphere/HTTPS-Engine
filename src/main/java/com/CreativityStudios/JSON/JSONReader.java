/**
 * JSONReader class
 *
 * Helps parse JSON Objects and Arrays
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.JSON;

import com.CreativityStudios.Database.DatabaseCredentials;
import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import java.io.StringReader;
import java.util.logging.Logger;

public class JSONReader {
    private static JsonArray jsonArray;
    private static JsonObject jsonObject;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static JsonArray jsonStringToJsonArray(String jsonString) {
        jsonArray = Json.createReader(new StringReader(jsonString)).readArray();
        return jsonArray;
    }

    public static JsonObject jsonStringToJsonObject(String jsonString) {
        jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
        return jsonObject;
    }


    public static String[] parseJsonArrayValueAsStringArray(String key) {
        String[] jsonValuesAsString = new String[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++) {
            jsonValuesAsString[i] = removeQuotationsFromString(jsonArray.getJsonObject(i).get(key).toString());
        }
        return jsonValuesAsString;
    }

    /**
     * Parses a JsonObject into a String representation
     * @param object JsonObject to convert
     * @return String representation of the JsonObject
     */
    public static String parseJsonObjectAsString(JsonObject object) {
        return String.valueOf(object);
    }

    public static DatabaseCredentials[] parseJsonObjectAsDatabaseCredentials(JsonObject object) {
        String url = object.getString("DatabaseURL");
        JsonArray credentialArray = object.getJsonArray("databaseCredentials");
        DatabaseCredentials[] credentials = new DatabaseCredentials[credentialArray.size()];

        for(int i = 0; i < credentialArray.size(); i++) {
            credentials[i] = new DatabaseCredentials();

            credentials[i].setDatabaseUrl(url);
            credentials[i].setDatabaseCredentialType(credentialArray.getJsonObject(i).getString("CredentialType"));
            credentials[i].setDatabaseUsername(credentialArray.getJsonObject(i).getString("Username"));
            credentials[i].setDatabasePassword(credentialArray.getJsonObject(i).getString("Password"));
        }
        return credentials;
    }

    /**
     * Takes strings from JSON objects and removes the associated quotations
     * from the string representations
     * @param strToRemoveQuotations String received from the JSON object
     * @return string without the quotations surrounding it
     */
    private static String removeQuotationsFromString(String strToRemoveQuotations) {
        String returnStr = strToRemoveQuotations.substring(1);
        return returnStr.substring(0, returnStr.length() - 1);
    }
}
