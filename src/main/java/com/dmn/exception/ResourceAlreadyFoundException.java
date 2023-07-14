package com.dmn.exception;

public class ResourceAlreadyFoundException extends RuntimeException {
    public ResourceAlreadyFoundException(String message) {
        super(message);
    }
}
