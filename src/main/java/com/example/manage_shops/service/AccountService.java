package com.example.manage_shops.service;

import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.request.RequestAccount;

public interface AccountService {
    void updateAccount(RequestAccount requestAccount) throws MyValidateException;

    void deleteAccount(RequestAccount requestAccount) throws MyValidateException;
}
