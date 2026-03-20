package com.CreativityStudios.File;

import java.io.*;

public class FileReader {
    private File file;
    private BufferedReader reader;

    public FileReader(String pathToFile) throws FileNotFoundException {
        file = new File(pathToFile);
        reader = new BufferedReader(new java.io.FileReader(file));
    }
    // TODO fix closeFile() causing a IOException for closing the stream
    private void closeFile() throws IOException {

    }

    public String readFileToString() throws IOException {
        String stringFromFile = "";
        while(reader.ready()) {
            stringFromFile = stringFromFile.concat(reader.readLine());
        }
        closeFile();
        return stringFromFile;
    }
}
