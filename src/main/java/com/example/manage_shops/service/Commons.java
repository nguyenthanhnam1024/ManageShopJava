package com.example.manage_shops.service;

import com.example.manage_shops.entity.Account;
import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.AccountRepo;
import com.example.manage_shops.repository.ShopRepo;
import com.example.manage_shops.repository.UserRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class Commons {
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

    public Account checkAccountInDatabase(Account account) throws MyValidateException {
        Optional<Account> accountOptional = accountRepo.findByUserName(account.getUserName());
        if (accountOptional.isPresent()) {
            throw new MyValidateException("Account is present");
        }
        return accountOptional.get();
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