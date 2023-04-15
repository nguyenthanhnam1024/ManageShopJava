package com.example.manage_shops.service;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.xml.bind.ValidationException;
import java.util.List;

@Service
public class Commons {
    public ResponseEntity<?> handleExceptionInBindingResult(BindingResult result) {
            List<ObjectError> errors = result.getAllErrors();
            return ResponseEntity.badRequest().body(errors.stream().map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .toArray(String[]::new));
    }

    public void validateRoleId(List<Integer> listRoleId, int roleId) throws ValidationException {
        boolean valid = false;
        for (Integer idRole : listRoleId) {
            if (idRole == roleId) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new ValidationException("you no right access");
        }
    }
}