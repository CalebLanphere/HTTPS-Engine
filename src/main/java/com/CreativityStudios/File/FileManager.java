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
import java.io.UnsupportedEncodingException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

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
    public static boolean createFileAtPath(String pathToAddFileAt, String fileName) throws IOException {
        file = new File(pathToAddFileAt + ResourcePaths.SEPARATOR + fileName);
        return file.createNewFile();
    }

    /**
     * Creates a directory at the requested path inside the PWD
     *
     * @param pathToAddFileAt Pathway to have the directory generated
     * @return boolean if directory creation was successful
     * @throws IOException If the communication chain for the file is interrupted
     */
    public static boolean createDirectoryAtPath(String pathToAddFileAt) throws IOException {
        file = new File(pathToAddFileAt);
        return file.mkdir();
    }

    // @TODO properly create hidden file
    public static boolean createFileAtPath(String pathToAddFileAt, String fileName, boolean createHiddenFile) throws IOException {
        boolean result;

        if(System.getProperty("os.name").equals("Windows_NT")) {
            file = new File(pathToAddFileAt + ResourcePaths.SEPARATOR + fileName);
            Files.setAttribute(Path.of(pathToAddFileAt + ResourcePaths.SEPARATOR + fileName), "dos:hidden", true);
        } else {
            file = new File(pathToAddFileAt + ResourcePaths.SEPARATOR + "." + fileName);
        }
        result = file.createNewFile();

        return result;
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
