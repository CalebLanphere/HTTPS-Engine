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

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.stream.JsonParser;

import java.io.StringReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JSONReader {
    private static JsonArray jsonArray;
    private static JsonObject jsonObject;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void jsonStringToJsonArray(String jsonString) {
        jsonArray = Json.createReader(new StringReader(jsonString)).readArray();
    }

    public static void jsonStringToJsonObject(String jsonString) {
        jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
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
