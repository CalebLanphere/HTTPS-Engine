package com.CreativityStudios.File;

import com.CreativityStudios.HTTPS.HTTPHeaders.HTTPContentType;

public class FileEndpoint {
    private String endpoint;
    private String fileLocation;
    private String dataType;

    public FileEndpoint(String endpoint, String fileLocation, String dataType) {
        this.endpoint = endpoint;
        this.fileLocation = fileLocation;
        this.dataType = dataType;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getFileLocation() {
        return fileLocation;
    }

    public String getHttpCompatibleDataType() {
        return mapDataTypeToHTTPDataType();
    }

    private String mapDataTypeToHTTPDataType() {
        return switch (dataType) {
            case ".aac" -> HTTPContentType.AAC;
            case ".abw" -> HTTPContentType.ABW;
            case ".apng" -> HTTPContentType.APNG;
            case ".arc" -> HTTPContentType.ARC;
            case ".avif" -> HTTPContentType.AVIF;
            case ".asz" -> HTTPContentType.ASZ;
            case ".bin" -> HTTPContentType.BIN;
            case ".bmp" -> HTTPContentType.BMP;
            case ".bz" -> HTTPContentType.BZ;
            case ".bz2" -> HTTPContentType.BZ2;
            case ".cda" -> HTTPContentType.CDA;
            case ".csh" -> HTTPContentType.CSH;
            case ".css" -> HTTPContentType.CSS;
            case ".csv" -> HTTPContentType.CSV;
            case ".doc" -> HTTPContentType.DOC;
            case ".docx" -> HTTPContentType.DOCX;
            case ".eot" -> HTTPContentType.EOT;
            case ".epub" -> HTTPContentType.EPUB;
            case ".gzip" -> HTTPContentType.GZIP;
            case ".gif" -> HTTPContentType.GIF;
            case ".htm" -> HTTPContentType.HTM;
            case ".html" -> HTTPContentType.HTML;
            case ".ico" -> HTTPContentType.ICO;
            case ".ics" -> HTTPContentType.ICS;
            case ".jar" -> HTTPContentType.JAR;
            case ".jpeg" -> HTTPContentType.JPEG;
            case ".jpg" -> HTTPContentType.JPG;
            case ".js" -> HTTPContentType.JS;
            case ".json" -> HTTPContentType.JSON;
            case ".jsonld" -> HTTPContentType.JSONLD;
            case ".md" -> HTTPContentType.MD;
            case ".mid" -> HTTPContentType.MID;
            case ".midi" -> HTTPContentType.MIDI;
            case ".mjs" -> HTTPContentType.MJS;
            case ".mp3" -> HTTPContentType.MP3;
            case ".mp4" -> HTTPContentType.MP4;
            case ".mpeg" -> HTTPContentType.MPEG;
            case ".mpkg" -> HTTPContentType.MPKG;
            case ".odp" -> HTTPContentType.ODP;
            case ".dos" -> HTTPContentType.ODS;
            case ".odt" -> HTTPContentType.ODT;
            case ".oga" -> HTTPContentType.OGA;
            case ".ogv" -> HTTPContentType.OGV;
            case ".ogx" -> HTTPContentType.OGX;
            case ".opus" -> HTTPContentType.OPUS;
            case ".otf" -> HTTPContentType.OTF;
            case ".png" -> HTTPContentType.PNG;
            case ".pdf" -> HTTPContentType.PDF;
            case ".php" -> HTTPContentType.PHP;
            case ".ppt" -> HTTPContentType.PPT;
            case ".pptx" -> HTTPContentType.PPTX;
            case ".rar" -> HTTPContentType.RAR;
            case ".rtf" -> HTTPContentType.RTF;
            case ".sh" -> HTTPContentType.SH;
            case ".svg" -> HTTPContentType.SVG;
            case ".tar" -> HTTPContentType.TAR;
            case ".tif" -> HTTPContentType.TIF;
            case ".tiff" -> HTTPContentType.TIFF;
            case ".ts" -> HTTPContentType.TS;
            case ".ttf" -> HTTPContentType.TTF;
            case ".txt" -> HTTPContentType.TXT;
            case ".vsd" -> HTTPContentType.VSD;
            case ".wav" -> HTTPContentType.WAV;
            case ".weba" -> HTTPContentType.WEBA;
            case ".webm" -> HTTPContentType.WEBM;
            case ".webmanifest" -> HTTPContentType.WEBMANIFEST;
            case ".webp" -> HTTPContentType.WEBP;
            case ".woff" -> HTTPContentType.WOFF;
            case ".woff2" -> HTTPContentType.WOFF2;
            case ".xhtml" -> HTTPContentType.XHTML;
            case ".xls" -> HTTPContentType.XLS;
            case ".xlsx" -> HTTPContentType.XLSX;
            case ".xml" -> HTTPContentType.XML;
            case ".xul" -> HTTPContentType.XUL;
            case ".zip" -> HTTPContentType.ZIP;
            case ".7z" -> HTTPContentType.SEVENZ;
            default -> HTTPContentType.BIN;
        };
    }
}
