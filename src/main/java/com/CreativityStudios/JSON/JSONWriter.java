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

import com.CreativityStudios.GCOP.EOCPElement;
import com.CreativityStudios.GCOP.EOCPValues;
import jakarta.json.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class JSONWriter {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    /**
     * Creates the EOCPElement JsonArray to communicate over the web
     *
     * @param eocpElements All elements to convert into a JsonArray
     * @return JsonArray created JsonArray
     */
    public static JsonArray createEOCPJSONArray(ArrayList<EOCPElement> eocpElements) {
        JsonArrayBuilder builder = Json.createArrayBuilder();

        for(EOCPElement element : eocpElements) {
            builder.add(element.getEOCPElementAsJsonObject());
        }

        return builder.build();
    }

    /**
     * Converts a EOPCElement into a JsonObject for communications over the web
     *
     * @param eopcObject EOCPElement object to convert into a JsonObject
     * @return JsonObject created JsonObject
     */
    public static JsonObject createECOPJSONObject(EOCPElement eopcObject) {
        JsonObjectBuilder builder = Json.createObjectBuilder();
        EOCPValues values = eopcObject.getValues();

        if(values.getHtmlType() != null) {
            builder.add("Type", values.getHtmlType());
        }
        if(values.getHtmlContent() != null) {
            builder.add("Content", values.getHtmlContent());
        }
        if(values.getHtmlClass() != null) {
            builder.add("Class", values.getHtmlClass());
        }
        if(values.getHtmlId() != null) {
            builder.add("Id", values.getHtmlId());
        }
        if(values.getHtmlStyle() != null) {
            builder.add("Style", values.getHtmlStyle());
        }
        if(values.getHtmlChildren() != null) {
            JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
            for(EOCPElement element : values.getHtmlChildren()) {
                arrBuilder.add(element.getEOCPElementAsJsonObject());
            }
            builder.add("Children", arrBuilder.build());
        }

        return builder.build();
    }
}
