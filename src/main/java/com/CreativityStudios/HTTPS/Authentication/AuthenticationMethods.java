package com.CreativityStudios.HTTPS.Authentication;

public class AuthenticationMethods {
    /** Digest Authentication:
     *
     * Uses the username, password, nonce, */
    public static final String DIGEST = "Digest";
    /** Basic Authentication:
     *
     * Encodes the username and password together with a ':' separating them
     *
     * Provides no security for transmission of the login credentials
     */
    public static final String BASIC = "Basic";
    /** Bearer Authentication:
     *
     * Creates a token for the user to use for authentication based on a server kept secret
     *
     * Great for stateless authentication methods, especially for load-balanced systems
     */
    public static final String TOKEN = "Bearer";
}
