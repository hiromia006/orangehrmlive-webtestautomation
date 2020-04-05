package com.orangehrmlive.web.fluentlenium.page;

public enum UserRole {
    ESS("ESS"),
    ADMIN("Admin");

    private String key;

    UserRole(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
