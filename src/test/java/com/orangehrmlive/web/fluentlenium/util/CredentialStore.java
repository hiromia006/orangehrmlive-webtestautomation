package com.orangehrmlive.web.fluentlenium.util;

import java.util.HashMap;
import java.util.Map;

public class CredentialStore {

    private static CredentialStore instance;

    private Map<String, String> credentials;
    private boolean populated;


    private CredentialStore() {
        credentials = new HashMap<>();
        populated = false;
    }

    public static CredentialStore instance() {
        if (instance == null) {
            instance = new CredentialStore();
        }
        return instance;
    }

    public boolean has(String key) {
        return credentials.containsKey(key);
    }

    public String get(String key) {
        return credentials.get(key);
    }

    public void put(String key, String value) {
        credentials.put(key, value);
    }

    private boolean isPopulated() {
        return populated;
    }

    public boolean isNotPopulated() {
        return !isPopulated();
    }

    public void setPopulated(boolean populated) {
        this.populated = populated;
    }
}
