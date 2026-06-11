package com.caraveo.gateway.infrastructure.exception;

import java.util.Map;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public Map<String, String> handleRuntime(
            RuntimeException ex) {

        return Map.of(
                "error",
                ex.getMessage());
    }
}