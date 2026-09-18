/**
 * FileWriter class
 *
 * Loads, writes, and saves to files
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.File;

import com.CreativityStudios.JSON.JSONReader;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileWriter {
    private File file;
    private PrintStream writer;

    /**
     * Gets the file at the provided pathway and prepares it for writing
     * @param pathToFile Pathway to the file to write into
     * @throws FileNotFoundException If the file is not found
     */
    public FileWriter(String pathToFile) throws FileNotFoundException {
        file = new File(pathToFile);
        writer = new PrintStream(new FileOutputStream(file));
    }

    public boolean writeJsonToFile(JsonObject object) {
        writer.print(JSONReader.parseJsonObjectAsString(object));
        writer.flush();

        return true;
    }

    public boolean writeJsonToFile(JsonArray jsonArray) {
        writer.print(JSONReader.parseJsonArrayAsString(jsonArray));
        writer.flush();

        return true;
    }

}
