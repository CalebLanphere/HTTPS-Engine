package com.CreativityStudios.File;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class FileWriter {
    private File file;
    private PrintStream writer;

    public FileWriter(String pathToFile) throws FileNotFoundException {
        file = new File(pathToFile);
        writer = new PrintStream(new FileOutputStream(file));
    }

}
