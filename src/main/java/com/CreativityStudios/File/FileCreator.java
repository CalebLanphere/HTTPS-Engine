package com.CreativityStudios.File;

import java.io.File;
import java.io.IOException;

public class FileCreator {
    private static File file;

    public static boolean doesFileExist(String pathToFile) {
        file = new File(pathToFile);
        return file.exists();
    }

    public static boolean createFileAtPath(String pathToFile) throws IOException {
        file = new File(pathToFile);
        return file.createNewFile();
    }

    public static boolean createFileAtPath(String pathToFile, boolean createHiddenFile) throws IOException {
        file = new File(pathToFile);
        return file.createNewFile();


    }

    public static String getAbsolutePath(String pathToFile) {
        file = new File(pathToFile);
        return file.getAbsolutePath();
    }

    public static boolean isReadable(String pathToFile) {
        file = new File(pathToFile);
        return file.canRead();
    }

}
