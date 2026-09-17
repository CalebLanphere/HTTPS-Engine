package com.CreativityStudios.Initialization;

import com.CreativityStudios.File.FileManager;
import com.CreativityStudios.File.ResourcePaths;

import java.io.IOException;

public class HTTPSServerInitialization {

    public static boolean readyToInitialize() throws IOException {
        if(!directoriesPresent()) {
            return false;
        }
        if(!filesPresent()) {
            return false;
        }

        return true;
    }

    private static boolean filesPresent() {
        if (!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_ENDPOINTS
                + ResourcePaths.SEPARATOR + "Endpoints.json")) {
            return false;
        }
        if (!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_SSL
                + ResourcePaths.SEPARATOR + "clientkeystore")) {
            return false;
        }
        if (!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_DATABASE
                + ResourcePaths.SEPARATOR + ".dbaccess.json") ||
                FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_DATABASE
                        + ResourcePaths.SEPARATOR + "dbaccess.json")) {
            return false;
        }

        return true;
    }

    private static boolean directoriesPresent() {
        if (!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES)) {
            return false;
        }
        if (!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_ENDPOINTS)) {
            return false;
        }
        if (!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_DATABASE)) {
            return false;
        }
        if (!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_SSL)) {
            return false;
        }
        if (!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_WEB)) {
            return false;
        }

        return true;
    }
}
