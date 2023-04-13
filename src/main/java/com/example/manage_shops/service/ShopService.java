package com.example.manage_shops.service;

import com.example.manage_shops.entity.Shop;

import java.util.List;

public interface ShopService {
    List<Shop> getAllShop();

    void saveShop(Shop shop);

    void updateShop(Shop shop);

    void deleteShop(Long id);

    List<Shop> searchShopByKeyword(String keyword);
}
