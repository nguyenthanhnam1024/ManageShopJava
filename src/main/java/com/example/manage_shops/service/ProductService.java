package com.example.manage_shops.service;

import com.example.manage_shops.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAllProductByIdShop(int idShop);
}
