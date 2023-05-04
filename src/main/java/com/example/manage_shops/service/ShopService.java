package com.example.manage_shops.service;

import com.example.manage_shops.dto.ShopDTO;
import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.exception.MyValidateException;

import java.util.List;

public interface ShopService {
    List<ShopDTO> getAllShop(int roleIdOfUser) throws MyValidateException;

    ShopDTO getShopById(int shopId, int roleIdOfUser) throws MyValidateException;

    ShopDTO saveShop(Shop shop, int roleIdOfUser) throws MyValidateException;

    ShopDTO updateShop(Shop shop, int roleIdOfUser) throws MyValidateException;

    ShopDTO deleteShop(int shopId, int roleIdOfUser) throws MyValidateException;

    List<ShopDTO> getShopByKeyword(String keyword, int roleIdOfUser) throws MyValidateException;

    ShopDTO mapIntoShopDTO(Shop shop);

    List<ShopDTO> mapIntoListShopDTO(List<Shop> shopList);
}
