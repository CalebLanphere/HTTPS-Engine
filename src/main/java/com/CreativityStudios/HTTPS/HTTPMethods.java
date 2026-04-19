package com.CreativityStudios.HTTPS;

public class HTTPMethods {
    /** For grabbing contents from the server */
    public static final String GET = "GET";
    /** For putting content into the server at a given location with the contents of the request body */
    public static final String POST = "POST";
    /** For replacing content on the server with the contents of the request body */
    public static final String PUT = "PUT";
    /** For deleting content off of the server */
    public static final String DELETE = "DELETE";
    /** For getting the options for HTTP requests. Used by the client browser */
    public static final String OPTIONS = "OPTIONS";
    /** Runs a message loop-back test to the source */
    public static final String TRACE = "TRACE";
    /** For putting a partial change onto a specified resource */
    public static final String PATCH = "PATCH";
    /** For getting the results from a GET request without the body */
    public static final String HEAD = "HEAD";
    /** For establishing a tunnel from the server to the specified resource */
    public static final String CONNECT = "CONNECT";
}
