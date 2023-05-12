package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Account;
import com.example.manage_shops.entity.User;
import com.example.manage_shops.repository.AccountRepo;
import com.example.manage_shops.service.SecurityService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityServiceImp implements SecurityService {
    private final AccountRepo accountRepo;

    @Override
    public Map<String, String> errorCheckAccountMap(Account account) {
        Map<String, String> errorMap = new HashMap<>();
        Optional<Account> accountOptional = accountRepo.findByUserName(account.getUserName());
        if (accountOptional.isPresent()) {
            if (accountOptional.get().getPassword().isEmpty()) {
                errorMap.put("password", "password not found");
                return errorMap;
            }
        }
        errorMap.put("userName", "user name not found");
        if (!accountRepo.findByPassword(account.getPassword()).isPresent()) {
            errorMap.put("password", "password not found");
        }
        return errorMap;
    }

    @Override
    public void registerUser(User user, Account account) {
        if (accountRepo.findByUserName(account.getUserName()).isPresent()) {
            throw new
        }
    }
}
