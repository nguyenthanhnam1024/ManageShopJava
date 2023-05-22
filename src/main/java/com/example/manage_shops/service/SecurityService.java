package com.example.manage_shops.service;

import com.example.manage_shops.dto.RequestLogin;
import com.example.manage_shops.dto.RequestRegister;
import com.example.manage_shops.dto.ResponseLogin;
import com.example.manage_shops.entity.Shop;

import java.util.List;
import java.util.Map;

public interface SecurityService {
     List<Shop> getShopList();

     String errorCheckAccountMap(RequestLogin requestLogin);

     Map<String, String> errorCheckRequestRegisterMap(RequestRegister requestRegister);

     void registerUser(RequestRegister requestRegister);

     ResponseLogin responseLogin(String userName);
}
