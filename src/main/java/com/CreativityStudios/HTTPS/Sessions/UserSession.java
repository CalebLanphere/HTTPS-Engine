package com.CreativityStudios.HTTPS.Sessions;

import com.CreativityStudios.HTTPS.Authentication.AuthenticationResult;

import java.sql.Timestamp;

public class UserSession {
    private AuthenticationResult authResult;
    private Timestamp expiryTime;

    public UserSession(AuthenticationResult authResult, Timestamp expiryTime) {
        this.authResult = authResult;
        this.expiryTime = expiryTime;
    }

    public Timestamp getExpiryTime() {
        return expiryTime;
    }

    public String getDomain() {
        return authResult.getUserDomain();
    }

    public String getEmail() {
        return authResult.getUserEmail();
    }

    public AuthenticationResult getAuthenticationResult() {
        return authResult;
    }

    public String toString() {
        return "Session:" + new String("\n userEmail: " + authResult.getUserEmail() + "\n userDomain: " + authResult.getUserDomain() + "\n Session expires: " + expiryTime).indent(4);
    }
}
