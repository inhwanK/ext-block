package com.example.extblock.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionAdvice {

    @ExceptionHandler(InvalidExtensionException.class)
    public String extensionExceptionHandler() {
        return "예외 테스트";
    }

}
