package com.example.manage_shops.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class Commons {

    public Map<String, String> handleExceptionInBindingResult(BindingResult result) {
        Map<String, String> errorValidateMap = new HashMap<>();
        for (ObjectError error : result.getAllErrors()) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errorValidateMap.put(fieldName, errorMessage);
        }
        return errorValidateMap;
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