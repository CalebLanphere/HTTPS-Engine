package com.CreativityStudios.HTTPS.Sessions;

public class UserToken {
    private String authToken;

    public UserToken(String token) {
        authToken = token;
    }

    public String getAuthToken() {
        return authToken;
    }
}
