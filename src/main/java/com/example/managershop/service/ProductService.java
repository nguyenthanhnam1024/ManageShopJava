package com.example.managershop.service;

import com.example.managershop.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> getAllProduct();

    public void saveProduct(Product product);

    public void updateProduct(Product product);

    public void deleteProductById(Long id);
}
