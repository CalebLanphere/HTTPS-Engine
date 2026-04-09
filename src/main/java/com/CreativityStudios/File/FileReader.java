/**
 * FileReader class
 *
 * Handles opening, reading, and closing of files
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.File;

import java.io.*;

public class FileReader {
    private File file;
    private BufferedReader reader;

    /**
     * Opens the file at the provided path
     * @param pathToFile Path to the file that is desired to be opened
     * @throws FileNotFoundException If the file cannot be found
     */
    public FileReader(String pathToFile) throws FileNotFoundException {
        file = new File(pathToFile);
        reader = new BufferedReader(new java.io.FileReader(file));
    }
    // TODO fix closeFile() causing a IOException for closing the stream
    private void closeFile() throws IOException {

    }

    /**
     * Read the contents of a file to a String
     * @return String file as a string
     * @throws IOException if the file reading was interrupted
     */
    public String readFileToString() throws IOException {
        String stringFromFile = "";
        while(reader.ready()) {
            stringFromFile = stringFromFile.concat(reader.readLine());
        }
        closeFile();
        return stringFromFile;
    }
}
