package com.CreativityStudios.File;

import java.nio.file.FileSystems;

public class ResourcePaths {
    public static final String SEPARATOR = FileSystems.getDefault().getSeparator();
    public static final String PWD = System.getenv("PWD");
    public static final String PWD_RESOURCES = PWD + SEPARATOR + "Resources";
    public static final String PWD_RESOURCES_ENDPOINTS = PWD_RESOURCES + "Endpoints";
    public static final String PWD_RESOURCES_DATABASE = PWD_RESOURCES + "Database";
    public static final String PWD_RESOURCES_SSL = PWD_RESOURCES + "SSL";
    public static final String PWD_RESOURCES_WEB = PWD_RESOURCES + "Web";
}
