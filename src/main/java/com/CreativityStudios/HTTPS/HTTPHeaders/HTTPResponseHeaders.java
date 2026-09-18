package com.CreativityStudios.HTTPS.HTTPHeaders;

public class HTTPResponseHeaders {
    /** Defines the method used for authentication to access a resource */
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    /** Defines the proxy method that should be used for authentication for accessing a resource */
    public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";
    /** Rules for caching objects | Can contain multiple options for values */
    public static final String CACHE_RULES = "Cache-Control";
    /** Which cache on the client to clear | Can contain multiple options for values */
    public static final String CLEAR_SITE_DATA = "Clear-Site-Data";
    /** Time to consider content in cache expired */
    public static final String EXPIRES = "Expires";
}
