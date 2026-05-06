package com.CreativityStudios.HTTPS.Sessions;

import com.sun.net.httpserver.Headers;

public class BearerToken implements Token{
    private String bearerToken;

    public BearerToken(String token) {
        bearerToken = token;
    }

    public BearerToken() {}

    @Override
    public String getToken() {
        return bearerToken;
    }

    @Override
    public void setToken(Headers headers) {
        if(headers.get("Token") != null) {
            bearerToken = headers.get("Token").getFirst();
        }
    }
}
