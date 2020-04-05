package com.orangehrmlive.web.fluentlenium.util;

public class Credential {

    private String baseUrl;
    private String username;
    private String password;

    public Credential(String baseUrl, String username, String password) {
        this.baseUrl = baseUrl;
        this.username = username;
        this.password = password;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

