package com.example.manage_shops.service;

import com.example.manage_shops.entity.Role;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Component
public class Commons {
    ResponseEntity<?> handleExceptionInBindingResult(BindingResult result) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.badRequest().body(errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new));
        }
}
