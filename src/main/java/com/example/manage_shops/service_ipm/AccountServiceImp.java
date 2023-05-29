package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Account;
import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.AccountRepo;
import com.example.manage_shops.repository.UserRepo;
import com.example.manage_shops.request.RequestAccount;
import com.example.manage_shops.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccountServiceImp implements AccountService {
    private final AccountRepo accountRepo;
    private final UserRepo userRepo;

    @Override
    public void updateAccount(RequestAccount requestAccount) throws MyValidateException {
        Optional<Account> accountOptional = accountRepo.findByUserName(requestAccount.getUserNameOfAccount());
        if (accountOptional.isPresent()) {
            BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
            if (bc.matches(requestAccount.getOldPassword(), accountOptional.get().getPassword())) {
                Optional<User> userOptional = userRepo.findByName(requestAccount.getNameOfUser());
                if (userOptional.isPresent()) {
                    if (accountOptional.get().getId().equals(userOptional.get().getIdAccount())) {
                        Account account = new Account();
                        account.setId(accountOptional.get().getId());
                        account.setUserName(requestAccount.getUserNameOfAccount());
                        account.setPassword(bc.encode(requestAccount.getNewPassword()));
                        accountRepo.save(account);
                        return;
                    }
                    throw new MyValidateException("User and account do not match");
                }
            }
            throw new MyValidateException("Request error");
        }
        throw new MyValidateException("do not update account");
    }

    @Override
    @Transactional
    public void deleteAccount(RequestAccount requestAccount) throws MyValidateException {
        Optional<Account> accountOptional = accountRepo.findByUserName(requestAccount.getUserNameOfAccount());
        if (accountOptional.isPresent()) {
            Optional<User> userOptional = userRepo.findByName(requestAccount.getNameOfUser());
            if (userOptional.isPresent()) {
                if (accountOptional.get().getId().equals(userOptional.get().getIdAccount())) {
                    try {
                        userRepo.deleteByName(requestAccount.getNameOfUser());
                        accountRepo.deleteById(accountOptional.get().getId());
                        return;
                    } catch (Exception ex) {
                        throw new MyValidateException("can not delete this account");
                    }
                }
                throw new MyValidateException("User and account do not match");
            }
        }
        throw new MyValidateException("can not delete this account");
    }
}