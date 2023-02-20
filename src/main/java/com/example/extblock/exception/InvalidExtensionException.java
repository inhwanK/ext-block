package com.example.extblock.exception;

import org.springframework.http.HttpStatus;

public class InvalidExtensionException extends RuntimeException {
    private final String errorCode = "400";
    private final HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

    public InvalidExtensionException(String message) {
        super(message);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
