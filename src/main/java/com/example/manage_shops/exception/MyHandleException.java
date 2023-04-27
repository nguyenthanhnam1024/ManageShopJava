package com.example.manage_shops.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MyHandleException {
    @ExceptionHandler(MyValidateException.class)
    public ResponseEntity<?> handleConflict(MyValidateException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
