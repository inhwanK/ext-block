package com.example.extblock.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler(InvalidExtensionException.class)
    public ResponseEntity<ExceptionResponse> extensionExceptionHandler(
            InvalidExtensionException ex
    ) {
        return ExceptionResponse
                .toResponseEntity(ex);
    }

}
