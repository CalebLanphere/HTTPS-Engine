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
        switch(dataType) {
            case ".aac":
                return HTTPContentType.AAC;
            case ".abw":
                return HTTPContentType.ABW;
            case ".apng":
                return HTTPContentType.APNG;
            case ".arc":
                return HTTPContentType.ARC;
            case ".avif":
                return HTTPContentType.AVIF;
            case ".asz":
                return HTTPContentType.ASZ;
            case ".bin":
                return HTTPContentType.BIN;
            case ".bmp":
                return HTTPContentType.BMP;
            case ".bz":
                return HTTPContentType.BZ;
            case ".bz2":
                return HTTPContentType.BZ2;
            case ".cda":
                return HTTPContentType.CDA;
            case ".csh":
                return HTTPContentType.CSH;
            case ".css":
                return HTTPContentType.CSS;
            case ".csv":
                return HTTPContentType.CSV;
            case ".doc":
                return HTTPContentType.DOC;
            case ".docx":
                return HTTPContentType.DOCX;
            case ".eot":
                return HTTPContentType.EOT;
            case ".epub":
                return HTTPContentType.EPUB;
            case ".gzip":
                return HTTPContentType.GZIP;
            case ".gif":
                return HTTPContentType.GIF;
            case ".htm":
                return HTTPContentType.HTM;
            case ".html":
                return HTTPContentType.HTML;
            case ".ico":
                return HTTPContentType.ICO;
            case ".ics":
                return HTTPContentType.ICS;
            case ".jar":
                return HTTPContentType.JAR;
            case ".jpeg":
                return HTTPContentType.JPEG;
            case ".jpg":
                return HTTPContentType.JPG;
            case ".js":
                return HTTPContentType.JS;
            case ".json":
                return HTTPContentType.JSON;
            case ".jsonld":
                return HTTPContentType.JSONLD;
            case ".md":
                return HTTPContentType.MD;
            case ".mid":
                return HTTPContentType.MID;
            case ".midi":
                return HTTPContentType.MIDI;
            case ".mjs":
                return HTTPContentType.MJS;
            case ".mp3":
                return HTTPContentType.MP3;
            case ".mp4":
                return HTTPContentType.MP4;
            case ".mpeg":
                return HTTPContentType.MPEG;
            case ".mpkg":
                return HTTPContentType.MPKG;
            case ".odp":
                return HTTPContentType.ODP;
            case ".dos":
                return HTTPContentType.ODS;
            case ".odt":
                return HTTPContentType.ODT;
            case ".oga":
                return HTTPContentType.OGA;
            case ".ogv":
                return HTTPContentType.OGV;
            case ".ogx":
                return HTTPContentType.OGX;
            case ".opus":
                return HTTPContentType.OPUS;
            case ".otf":
                return HTTPContentType.OTF;
            case ".png":
                return HTTPContentType.PNG;
            case ".pdf":
                return HTTPContentType.PDF;
            case ".php":
                return HTTPContentType.PHP;
            case ".ppt":
                return HTTPContentType.PPT;
            case ".pptx":
                return HTTPContentType.PPTX;
            case ".rar":
                return HTTPContentType.RAR;
            case ".rtf":
                return HTTPContentType.RTF;
            case ".sh":
                return HTTPContentType.SH;
            case ".svg":
                return HTTPContentType.SVG;
            case ".tar":
                return HTTPContentType.TAR;
            case ".tif":
                return HTTPContentType.TIF;
            case ".tiff":
                return HTTPContentType.TIFF;
            case ".ts":
                return HTTPContentType.TS;
            case ".ttf":
                return HTTPContentType.TTF;
            case ".txt":
                return HTTPContentType.TXT;
            case ".vsd":
                return HTTPContentType.VSD;
            case ".wav":
                return HTTPContentType.WAV;
            case ".weba":
                return HTTPContentType.WEBA;
            case ".webm":
                return HTTPContentType.WEBM;
            case ".webmanifest":
                return HTTPContentType.WEBMANIFEST;
            case ".webp":
                return HTTPContentType.WEBP;
            case ".woff":
                return HTTPContentType.WOFF;
            case ".woff2":
                return HTTPContentType.WOFF2;
            case ".xhtml":
                return HTTPContentType.XHTML;
            case ".xls":
                return HTTPContentType.XLS;
            case ".xlsx":
                return HTTPContentType.XLSX;
            case ".xml":
                return HTTPContentType.XML;
            case ".xul":
                return HTTPContentType.XUL;
            case ".zip":
                return HTTPContentType.ZIP;
            case ".7z":
                return HTTPContentType.SEVENZ;
            default:
                return HTTPContentType.BIN;
        }
    }
}
