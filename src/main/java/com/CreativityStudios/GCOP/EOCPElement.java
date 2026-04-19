/**
 * EOCPElement class
 *
 * A class that outlines the Expirī Object Communications Protocol Version 1 (EOCPv1) object.
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */
package com.CreativityStudios.GCOP;

import com.CreativityStudios.JSON.JSONWriter;
import jakarta.json.JsonObject;

public class EOCPElement {
    private EOCPValues values;

    public EOCPElement(EOCPValues values) {
        this.values = values;
    }

    public EOCPElement() {
        this.values = null;
    }

    public EOCPValues getValues() {
        return values;
    }

    public void setValues(EOCPValues values) {
        this.values = values;
    }

    public JsonObject getEOCPElementAsJsonObject() {
        return JSONWriter.createECOPJSONObject(this);
    }

}
