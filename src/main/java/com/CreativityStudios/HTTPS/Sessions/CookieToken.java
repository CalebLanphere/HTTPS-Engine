package com.CreativityStudios.HTTPS.Sessions;

import com.sun.net.httpserver.Headers;

public class CookieToken implements Token{
    private String cookieToken;

    public CookieToken(String token) {
        cookieToken = token;
    }

    public CookieToken() {}

    @Override
    public String getToken() {
        return cookieToken;
    }

    @Override
    public void setToken(Headers headers) {
        if (headers.get("Cookie") != null) {
            for(String cookieEntry : headers.get("Cookie")) {
                if(cookieEntry.contains("session=")) {
                    cookieToken = cookieEntry.substring(cookieEntry.indexOf('=') + 1);
                }
            }
        } else {
            cookieToken = null;
        }
    }
}
