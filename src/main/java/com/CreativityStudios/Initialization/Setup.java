package com.CreativityStudios.Initialization;

import com.CreativityStudios.File.FileManager;
import com.CreativityStudios.File.FileWriter;
import com.CreativityStudios.File.ResourcePaths;
import com.CreativityStudios.JSON.JSONWriter;

import java.io.IOException;
import java.nio.file.FileSystems;

public class Setup {

    private static boolean setupDirectories() throws IOException {
        FileManager.createDirectoryAtPath(ResourcePaths.PWD_RESOURCES);
        FileManager.createDirectoryAtPath(ResourcePaths.PWD_RESOURCES_ENDPOINTS);
        FileManager.createDirectoryAtPath(ResourcePaths.PWD_RESOURCES_DATABASE);
        FileManager.createDirectoryAtPath(ResourcePaths.PWD_RESOURCES_SSL);
        FileManager.createDirectoryAtPath(ResourcePaths.PWD_RESOURCES_WEB);

        return true;
    }

    private static boolean setupFiles() throws IOException {
        FileManager.createFileAtPath(ResourcePaths.PWD_RESOURCES_ENDPOINTS
                + ResourcePaths.SEPARATOR, "Endpoints.json");
        FileManager.createFileAtPath(ResourcePaths.PWD_RESOURCES_DATABASE
                + ResourcePaths.SEPARATOR, "dbaccess.json", true);

        FileWriter fileWriter = new FileWriter(ResourcePaths.PWD_RESOURCES_DATABASE
                + ResourcePaths.SEPARATOR + "dbaccess.json");
        fileWriter.writeJsonToFile(JSONWriter.createDefaultDbAccessConfigJsonObject());

        fileWriter = new FileWriter(ResourcePaths.PWD_RESOURCES_ENDPOINTS
                + ResourcePaths.SEPARATOR + "Endpoints.json");
        fileWriter.writeJsonToFile(JSONWriter.createDefaultEndpointsJsonObject());
        return true;
    }
}
