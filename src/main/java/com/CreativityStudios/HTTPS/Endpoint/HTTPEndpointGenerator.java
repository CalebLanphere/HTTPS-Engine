package com.CreativityStudios.HTTPS.Endpoint;

import com.CreativityStudios.File.FileEndpoint;

import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HTTPEndpointGenerator {
    private static final Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
    private String PATH;
    private static final String DEFAULT_GET_HANDLER = "com.CreativityStudios.HTTPS.HTTPHandlers.HTTPDefaultGetHandler";

    public HTTPEndpointGenerator(String pathToNavigate) {
        PATH = pathToNavigate;
    }

    public EndpointConfiguration[] generateEndpointsInsideFolder() {
        ArrayList<FileEndpoint> createdFileEndpoints = navigateFolder(PATH, "");
        int amtOfEndpoints = createdFileEndpoints.size();
        EndpointConfiguration[] endpoints = new EndpointConfiguration[amtOfEndpoints];
        int index = 0;

        for(FileEndpoint entry : createdFileEndpoints) {
            endpoints[index] = new EndpointConfiguration(entry.getEndpoint(), DEFAULT_GET_HANDLER, false, entry.getFileLocation(), entry.getHttpCompatibleDataType());
            index++;
        }

        return endpoints;
    }

    public ArrayList<FileEndpoint> navigateFolder(String pathToNavigate, String pathToAddAtBeginningOfMapping) {
        File directory = new File(pathToNavigate);
        ArrayList<FileEndpoint> fileMappings = new ArrayList<>();
        int index = 0;

        for(File fileEntry : Objects.requireNonNull(directory.listFiles())) {
            if(fileEntry == null) {
                break;
            }

            if(fileEntry.isDirectory()) {
                ArrayList<FileEndpoint> mapsInsideSubDirectory = new HTTPEndpointGenerator(fileEntry.getPath()).navigateFolder(fileEntry.getPath(), "/" + fileEntry.getPath().split(PATH)[1]);
                fileMappings.addAll(mapsInsideSubDirectory);
            } else {
                if (fileEntry.getName().contains(".html")) {
                    if (fileEntry.getName().equals("index.html")) {
                        fileMappings.add(new FileEndpoint("/", fileEntry.getAbsolutePath(), ".html"));
                        continue;
                    }
                    if (!fileEntry.getName().contains(".DS_Store")) {
                        fileMappings.add(new FileEndpoint("/" + fileEntry.getName().substring(0, fileEntry.getName().indexOf('.')), fileEntry.getAbsolutePath(), ".html"));
                    }
                } else {
                    if (!fileEntry.getName().contains(".DS_Store")) {
                        fileMappings.add(new FileEndpoint(pathToAddAtBeginningOfMapping + "/" + fileEntry.getName(), fileEntry.getAbsolutePath(), fileEntry.getName().substring(fileEntry.getName().indexOf('.'))));
                    }
                }
            }
        }
        return fileMappings;
    }
}
