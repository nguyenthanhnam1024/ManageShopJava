package com.example.manage_shops.repository;

import com.example.manage_shops.entity.User;

import java.util.List;

public interface CustomUserRepo {
    List<User> customSearch(String keyword, String roleName, int idShop);
}
