package com.example.managershop.service;

import com.example.managershop.entity.Account;

import java.util.List;

public interface AccountService {
    List<Account> getAllAccount();

    void saveAccount(Account account);

    void updateAccount(Account account);

    void deleteAccount(Long id);
}
