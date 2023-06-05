package com.example.manage_shops.service;

import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.response.ResponseLogin;

import java.util.List;

public interface ShopService {
    void validateRole(String roleName) throws MyValidateException;

    List<Shop> getAllShop() throws MyValidateException;

    Shop getShopById(int shopId, ResponseLogin responseLogin) throws MyValidateException;

    Shop saveShop(Shop shop, ResponseLogin responseLogin) throws MyValidateException;

    Shop updateShop(Shop shop, ResponseLogin responseLogin) throws MyValidateException;

    Shop deleteShop(int shopId, ResponseLogin responseLogin) throws MyValidateException;

    List<Shop> getShopByKeyword(String keyword, ResponseLogin responseLogin) throws MyValidateException;
}
