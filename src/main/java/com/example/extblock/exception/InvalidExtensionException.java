package com.example.extblock.exception;

import org.springframework.http.HttpStatus;

public class InvalidExtensionException extends RuntimeException {
    private final String ERROR_CODE = "400";
    private final HttpStatus HTTP_STATUS = HttpStatus.BAD_REQUEST;

    public InvalidExtensionException(String message) {
        super(message);
    }
}
