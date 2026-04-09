/**
 * FileManager class
 *
 * Simplifies File creation and status operations
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.File;

import java.io.File;
import java.io.IOException;

public class FileManager {
    private static File file;

    /**
     * Checks to see if a file exists
     *
     * @param pathToFile File pathway to check for a file
     * @return boolean if the file exists
     */
    public static boolean doesFileExist(String pathToFile) {
        file = new File(pathToFile);
        return file.exists();
    }

    /**
     * Creates a file at the requested path
     *
     * @param pathToAddFileAt Pathway to create the file at
     * @return boolean if file creation was successful
     * @throws IOException If the communication chain for the file is interrupted
     */
    public static boolean createFileAtPath(String pathToAddFileAt) throws IOException {
        file = new File(pathToAddFileAt);
        return file.createNewFile();
    }

    // @TODO properly create hidden file
    public static boolean createFileAtPath(String pathToAddFileAt, boolean createHiddenFile) throws IOException {
        return false;
    }

    /**
     * Gets the absolute file path of a given local file path
     * @param pathToFile local path to a file
     * @return String absolute file path
     */
    public static String getAbsolutePath(String pathToFile) {
        file = new File(pathToFile);
        return file.getAbsolutePath();
    }

    /**
     * Checks if the file is readable
     *
     * @param pathToFile Path to the file
     * @return boolean if the file is readable or not
     */
    public static boolean isReadable(String pathToFile) {
        file = new File(pathToFile);
        return file.canRead();
    }

}
