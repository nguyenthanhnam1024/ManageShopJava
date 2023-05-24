package com.example.manage_shops.service;

import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.RoleEnum;
import com.example.manage_shops.repository.ShopRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Commons {
    private final ShopRepo shopRepo;

    public Map<String, String> handleExceptionInBindingResult(BindingResult result) {
        Map<String, String> errorValidateMap = new HashMap<>();
        for (ObjectError error : result.getAllErrors()) {
                String fieldName = ((FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errorValidateMap.put(fieldName, errorMessage);
        }
        return errorValidateMap;
    }

    public void validateShopForUserToQuery(int idShop, int idShopOfUser) throws MyValidateException {
        if (idShop != idShopOfUser) {
            throw new MyValidateException("You cannot operate in the current shop");
        }
        Optional<Shop> optionalShop = shopRepo.findById(idShop);
        if (!optionalShop.isPresent()) {
            throw new MyValidateException("Shop not found for you to add data") ;
        }
    }

    public void validateRole(List<String> roleNameList, String roleNameOfUser) throws MyValidateException {
        for (String roleName: roleNameList) {
            if (roleName.equals(roleNameOfUser)) {
                return;
            }
        }
        throw new MyValidateException("you do not have permission to perform this function");
    }
    public void validateRoleForADMIN(String roleNameOfUser) throws MyValidateException {
        if (RoleEnum.ADMIN.getRoleName().equals(roleNameOfUser)) {
            return;
        }
        throw new MyValidateException("you do not have permission to perform this function");
    }
}