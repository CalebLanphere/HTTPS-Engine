/**
 * HTTPStatus class
 *
 * Contains all common HTTP status codes that could be returned by the server
 *
 * Code Level Meanings:
 * 100: Information
 * 200: Success
 * 300: Redirection
 * 400: Client error
 * 500: Server error
 *
 * @author Caleb Lanphere
 *
 * Copyright 2026 Caleb Lanphere, All Rights Reserved.
 */

package com.CreativityStudios.HTTPS.HTTPHeaders;

public class HTTPStatus {
    /** The client should continue the request */
    public static final int CONTINUE = 100;
    /** The request to switch protocols was identified and accepted. Used from the UPGRADE header */
    public static final int SWITCHING_PROTOCOLS = 101;
    /** The response is ok, with body content attached */
    public static final int OK = 200;
    /** The content supplied was created on the server */
    public static final int CREATED = 201;
    /** The task requested was accepted, but not complete, and therefore will have no return */
    public static final int ACCEPTED = 202;
    /** The response is ok, without body content attached */
    public static final int NO_CONTENT = 204;
    /** Tells the client to reset the document which sent the request */
    public static final int RESET_CONTENT = 205;
    /** The content requested was moved to a new URI. Attach a new URL in the response */
    public static final int MOVED_PERMANENTLY = 301;
    /** The content requested was moved to a temporary URI */
    public static final int FOUND = 302;
    public static final int TEMPORARY_REDIRECT = 307;
    /** The request received by the client is incorrect or not usable */
    public static final int BAD_REQUEST = 400;
    /** The client must authenticate to perform the action requested */
    public static final int UNAUTHORIZED = 401;
    /** The client is not allowed to perform the action requested */
    public static final int FORBIDDEN = 403;
    /** The server cannot find the requested document */
    public static final int NOT_FOUND = 404;
    /** The method requested by the client is not allowed */
    public static final int METHOD_NOT_ALLOWED = 405;
    /** The access requested requires a proxy to be authenticated. Similar to status 401 */
    public static final int PROXY_AUTHENTICATION_REQUIRED = 407;
    /** The request has timed out */
    public static final int REQUEST_TIMEOUT = 408;
    /** The requested content was deleted without a redirect. Tells client to remove URI from cache */
    public static final int GONE = 410;
    /** The content attempted to upload to the server is of an unsupported type */
    public static final int UNSUPPORTED_MEDIA_TYPE = 415;
    /** The server cannot meet the expectation set in the EXCEPT request header */
    public static final int EXPECTATION_FAILED = 417;
    /** The content is properly formatted, but unprocessable for semantic reasons */
    public static final int UNPROCESSABLE_CONTENT = 422;
    /** Content at the requested URI is locked */
    public static final int LOCKED = 423;
    /** The server requires a different protocol to perform the requested action */
    public static final int UPGRADE_REQUIRED = 426;
    /** The requesting client has tried to send too many requests (Rate limiting) */
    public static final int TOO_MANY_REQUESTS = 429;
    /** The requested content is not accessible due to regulations of their country */
    public static final int UNAVAILABLE_FOR_LEGAL_REASONS = 451;
    /** The server has encountered an unknown internal error */
    public static final int INTERNAL_SERVER_ERROR = 500;
    /** The requested method is not currently implemented. Cannot be returned for GET or HEAD methods */
    public static final int NOT_IMPLEMENTED = 501;
    /** The server, while acting as a Gateway, received an incorrect response */
    public static final int BAD_GATEWAY = 502;
    /** The requested service is unavailable and the server is unable to process the request at that time */
    public static final int SERVICE_UNAVAILABLE = 503;
    /** The client HTTP version is not supported by the server */
    public static final int HTTP_VERSION_NOT_SUPPORTED = 505;
    /** The server does not have enough storage to complete the requested action */
    public static final int INSUFFICIENT_STORAGE = 508;
    /** Alerts to the client they need to authenticate to receive network access */
    public static final int NETWORK_AUTHENTICATION_REQUIRED = 511;
}
