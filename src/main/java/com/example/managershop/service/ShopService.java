package com.example.managershop.service;

import com.example.managershop.entity.Shop;

import java.util.List;

public interface ShopService {
    public List<Shop> getAllShop();
    public void saveShop(Shop shop);
    public void updateShop(Shop shop);
    public void deleteShop(Long id);
}
