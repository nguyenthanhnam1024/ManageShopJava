package com.example.manage_shops.service;

import com.example.manage_shops.entity.Role;
import com.example.manage_shops.repository.RoleRepo;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class Commons {
    private final RoleRepo roleRepo;

    public Commons(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

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
        Optional<Role> roleOptional = roleRepo.findById(roleId);
        if (!roleOptional.isPresent()) {
            return "this role of you no exist";
        }
        for (Integer idRole : listRoleId) {
            if (idRole == roleId) {
                return null;
            }
        }
        return "you no right access";
    }
}