package com.example.manage_shops.service;

import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.exception.MyValidateException;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ShopService {
    List<Shop> getAllShop() throws MyValidateException;

    Shop getShopById(HttpServletRequest httpServletRequest, int shopId) throws MyValidateException;

    Shop saveShop(HttpServletRequest httpServletRequest, Shop shop) throws MyValidateException;

    Shop updateShop(HttpServletRequest httpServletRequest, Shop shop) throws MyValidateException;

    Shop deleteShop(HttpServletRequest httpServletRequest, int shopId) throws MyValidateException;

    List<Shop> getShopByKeyword(HttpServletRequest httpServletRequest, String keyword) throws MyValidateException;
}
