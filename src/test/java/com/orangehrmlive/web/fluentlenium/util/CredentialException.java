package com.orangehrmlive.web.fluentlenium.util;

public class CredentialException extends RuntimeException {

    private static final long serialVersionUID = -9035301540281531502L;

    public CredentialException(String message) {
        super(message);
    }

    public CredentialException(String message, Throwable cause) {
        super(message, cause);
    }
}
