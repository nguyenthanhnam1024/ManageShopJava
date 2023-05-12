package com.example.manage_shops.service;

import com.example.manage_shops.entity.Account;
import com.example.manage_shops.entity.Role;
import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.AccountRepo;
import com.example.manage_shops.repository.RoleRepo;
import com.example.manage_shops.repository.ShopRepo;
import com.example.manage_shops.repository.UserRepo;
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
    private final RoleRepo roleRepo;
    private final AccountRepo accountRepo;
    private final UserRepo userRepo;
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

    public String validateRoleId(List<Integer> listRoleId, int roleIdOfUser) {
        Optional<Role> roleOptional = roleRepo.findById(roleIdOfUser);
        if (!roleOptional.isPresent()) {
            return "role of you no exist";
        }
        for (Integer idRole : listRoleId) {
            if (idRole == roleIdOfUser) {
                return null;
            }
        }
        return "you no right access";
    }

    public void checkAccountInDatabase(Account account) throws MyValidateException {
        if (accountRepo.findByUserName(account.getUserName()).isPresent()) {
            throw new MyValidateException("Account is present");
        }
    }

    public void checkUserInDatabase(User user) throws MyValidateException {
        if (userRepo.findByName(user.getName()).isPresent()) {
            throw new MyValidateException("user name is present");
        }
        if (!shopRepo.findById(user.getIdShop()).isPresent()) {
            throw new MyValidateException("shop is present");
        }
    }
}