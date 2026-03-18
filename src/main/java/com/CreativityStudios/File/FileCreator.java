package com.CreativityStudios.File;

import java.io.File;
import java.io.IOException;

public class FileCreator {
    private static File file;

    public static boolean doesFileExist(String pathToFile) {
        file = new File(pathToFile);
        return file.exists();
    }

    public static void createFileAtPath(String pathToFile) throws IOException {
        file = new File(pathToFile);
        file.createNewFile();
    }

}
