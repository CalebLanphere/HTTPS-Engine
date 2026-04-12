package com.CreativityStudios.HTTP.HTTPHeaders.Authentication;

public class HTTPAuthenticationHeaders {
    /** Defines the method used for authentication to access a resource */
    public static final String WWW_AUTHENTICATE = "WWW-Authenticate";
    /** Will contain the authentication details associated with a request */
    public static final String AUTHORIZATION = "Authorization";
    /** Defines the proxy method that should be used for authentication for accessing a resource */
    public static final String PROXY_AUTHENTICATE = "Proxy-Authenticate";
    /** Will contain the proxy authentication details assocated with a request */
    public static final String PROXY_AUTHORIZATION = "Proxy-Authorization";
}
