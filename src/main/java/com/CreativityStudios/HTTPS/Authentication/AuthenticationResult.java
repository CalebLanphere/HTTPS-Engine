package com.CreativityStudios.HTTPS.Authentication;

public class AuthenticationResult {
    private boolean isAuthSuccessful;
    private String userEmail;
    private String userDomain;

    public AuthenticationResult(boolean result) {
        isAuthSuccessful = result;
    }

    public AuthenticationResult(boolean result, String email, String domain) {
        isAuthSuccessful = result;
        userEmail = email;
        userDomain = domain;
    }

    public boolean isAuthenticationSuccessful() {
        return isAuthSuccessful;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserDomain() {
        return userDomain;
    }

    public String toString() {
        return "AuthenticationResult:" +  new String("\n IsAuthenticated: " + isAuthSuccessful + "\n userEmail: " + userEmail + "\n userDomain: " + userDomain).indent(4);
    }
}
