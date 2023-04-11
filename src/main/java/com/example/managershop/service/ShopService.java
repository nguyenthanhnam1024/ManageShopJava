package com.example.managershop.service;

import com.example.managershop.entity.Shop;

import java.util.List;

public interface ShopService {
    public List<Shop> getAllShop();
    public void saveShop(Shop shop);
}
