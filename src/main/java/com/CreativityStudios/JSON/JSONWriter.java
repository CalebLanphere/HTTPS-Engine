/**
 * JSONWriter class
 *
 * Helps generate JSON Objects and Arrays
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.JSON;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

import java.util.HashMap;
import java.util.Map;

public class JSONWriter {
    // TODO Generalize this method and create individual class bases to create JSON files from
    public static JsonObject createJsonTestObject(HashMap<String, String> valuesToAdd) {
        JsonObjectBuilder jsonBuilder = Json.createObjectBuilder();

        for(Map.Entry<String, String> entries : valuesToAdd.entrySet()) {
            jsonBuilder.add(entries.getKey(), entries.getValue());
        }

        return jsonBuilder.build();
    }
}
