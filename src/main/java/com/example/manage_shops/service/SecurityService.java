package com.example.manage_shops.service;

import com.example.manage_shops.dto.RequestLogin;
import com.example.manage_shops.dto.ResponseLogin;
import com.example.manage_shops.entity.Account;
import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;

import java.util.List;
import java.util.Map;

public interface SecurityService {
     List<Shop> getShopList();
     String errorCheckAccountMap(RequestLogin requestLogin);

     void registerUser(User user, Account account) throws MyValidateException;

     ResponseLogin responseLogin(String userName);
}
