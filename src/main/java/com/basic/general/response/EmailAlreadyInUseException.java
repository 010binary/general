package com.basic.general.response;

public class EmailAlreadyInUseException extends RuntimeException {
    public EmailAlreadyInUseException(String message) {
        super(message);
    }

    public EmailAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }
}