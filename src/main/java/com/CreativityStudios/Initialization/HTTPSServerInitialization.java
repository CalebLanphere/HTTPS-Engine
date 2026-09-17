package com.CreativityStudios.Initialization;

import com.CreativityStudios.File.FileManager;
import com.CreativityStudios.File.ResourcePaths;

import java.io.IOException;

public class HTTPSServerInitialization {

    public static boolean applicationSetupPreviously() throws IOException {
        if (FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES)) {
            return true;
        }
    }
}
