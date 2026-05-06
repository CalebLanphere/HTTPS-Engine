package com.CreativityStudios.HTTPS.Sessions;

import com.sun.net.httpserver.Headers;

public interface Token {
    public String getToken();
    public void setToken(Headers headers);
}
