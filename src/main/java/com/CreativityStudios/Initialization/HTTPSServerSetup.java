package com.CreativityStudios.Initialization;

import com.CreativityStudios.Exceptions.FailureToSetupApplicationException;
import com.CreativityStudios.File.FileManager;
import com.CreativityStudios.File.FileWriter;
import com.CreativityStudios.File.ResourcePaths;
import com.CreativityStudios.JSON.JSONWriter;

import java.io.IOException;

public class HTTPSServerSetup {

    public static boolean setupApplication() throws FailureToSetupApplicationException {
        try {
            if (!setupDirectories()) {
                return false;
            }
            if (!setupFiles()) {
                return false;
            }
        } catch(IOException e) {
            throw new FailureToSetupApplicationException(e.getMessage());
        }

        return true;
    }

    private static boolean setupDirectories() throws IOException {
        if(!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES)) {
            FileManager.createDirectoryAtPath(ResourcePaths.PWD_RESOURCES);
        }
        if(!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_ENDPOINTS)) {
            FileManager.createDirectoryAtPath(ResourcePaths.PWD_RESOURCES_ENDPOINTS);
        }
        if(!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_DATABASE)) {
            FileManager.createDirectoryAtPath(ResourcePaths.PWD_RESOURCES_DATABASE);
        }
        if(!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_SSL)) {
            FileManager.createDirectoryAtPath(ResourcePaths.PWD_RESOURCES_SSL);
        }
        if(!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_WEB)) {
            FileManager.createDirectoryAtPath(ResourcePaths.PWD_RESOURCES_WEB);
        }

        return true;
    }

    private static boolean setupFiles() throws IOException {
        FileWriter fileWriter;

        if(!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_ENDPOINTS
                + ResourcePaths.SEPARATOR + "Endpoints.json")) {

            FileManager.createFileAtPath(ResourcePaths.PWD_RESOURCES_ENDPOINTS
                    + ResourcePaths.SEPARATOR, "Endpoints.json");

            fileWriter = new FileWriter(ResourcePaths.PWD_RESOURCES_ENDPOINTS
                    + ResourcePaths.SEPARATOR + "Endpoints.json");
            fileWriter.writeJsonToFile(JSONWriter.createDefaultEndpointsJsonObject());
        }

        if(!FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_DATABASE
                + ResourcePaths.SEPARATOR + ".dbaccess.json") ||
                FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_DATABASE
                        + ResourcePaths.SEPARATOR + "dbaccess.json")) {

            FileManager.createFileAtPath(ResourcePaths.PWD_RESOURCES_DATABASE
                    + ResourcePaths.SEPARATOR, "dbaccess.json", true);

            if(FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_DATABASE + ResourcePaths.SEPARATOR + ".dbaccess.json")) {
                fileWriter = new FileWriter(ResourcePaths.PWD_RESOURCES_DATABASE
                        + ResourcePaths.SEPARATOR + ".dbaccess.json");
                fileWriter.writeJsonToFile(JSONWriter.createDefaultDbAccessConfigJsonObject());
            }

            if(FileManager.doesFileExist(ResourcePaths.PWD_RESOURCES_DATABASE + ResourcePaths.SEPARATOR + "dbaccess.json")) {
                fileWriter = new FileWriter(ResourcePaths.PWD_RESOURCES_DATABASE
                        + ResourcePaths.SEPARATOR + "dbaccess.json");
                fileWriter.writeJsonToFile(JSONWriter.createDefaultDbAccessConfigJsonObject());
            }
        }

        return true;
    }
}
