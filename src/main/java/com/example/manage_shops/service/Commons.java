package com.example.manage_shops.service;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.util.List;

@Service
public class Commons {
    public ResponseEntity<?> handleExceptionInBindingResult(BindingResult result) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.badRequest().body(errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new));
    }

    public String validateRoleId(List<Integer> listRoleId, int roleId) {
        for (Integer idRole : listRoleId) {
            if (idRole == roleId) {
                return null;
            }
        }
        return "you no right access";
    }
}