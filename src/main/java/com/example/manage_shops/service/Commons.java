package com.example.manage_shops.service;

import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.jwt.ExtractDataFromJwt;
import com.example.manage_shops.my_enum.RoleEnum;
import com.example.manage_shops.repository.ShopRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Commons {
    private final ShopRepo shopRepo;
    private final ExtractDataFromJwt extractDataFromJwt;

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

    public void validateRole(HttpServletRequest httpServletRequest, List<String> roleNameListLicensed) throws MyValidateException {
        List<String> roles = extractDataFromJwt.extractRoleNamesFromJwt(httpServletRequest);
        for (String roleNameOfUser: roles) {
            if (roleNameListLicensed.contains(roleNameOfUser)) {
                return;
            }
        }
        throw new MyValidateException("you do not have permission to perform this function");
    }

    public void validateRoleForADMIN(HttpServletRequest httpServletRequest) throws MyValidateException {
        List<String> roles = extractDataFromJwt.extractRoleNamesFromJwt(httpServletRequest);
        for (String roleName : roles) {
            if (RoleEnum.ADMIN.getRoleName().equals(roleName)) {
                return;
            }
        }
        throw new MyValidateException("you do not have permission to perform this function");
    }
}