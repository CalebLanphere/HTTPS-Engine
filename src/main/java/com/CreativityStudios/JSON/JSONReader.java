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
    private JsonArray jsonArray;
    private JsonObject jsonObject;
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void jsonStringToJsonArray(String jsonString) {
        jsonArray = Json.createReader(new StringReader(jsonString)).readArray();
    }

    public void jsonStringToJsonObject(String jsonString) {
        jsonObject = Json.createReader(new StringReader(jsonString)).readObject();
    }


    public String[] parseJsonArrayValueAsStringArray(String key) {
        String[] jsonValuesAsString = new String[jsonArray.size()];
        for(int i = 0; i < jsonArray.size(); i++) {
            jsonValuesAsString[i] = removeQuotationsFromString(jsonArray.getJsonObject(i).get(key).toString());
        }
        return jsonValuesAsString;
    }

    private String removeQuotationsFromString(String strToRemoveQuotations) {
        String returnStr = strToRemoveQuotations.substring(1);
        return returnStr.substring(0, returnStr.length() - 1);
    }
}
