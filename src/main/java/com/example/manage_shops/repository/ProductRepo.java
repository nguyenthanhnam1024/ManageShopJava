package com.example.manage_shops.repository;

import com.example.manage_shops.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findProductByIdShop(int idShop);
    Product findProductByName(String productName);
    List<Product> findByNameContainingIgnoreCase(String keyword);
    Optional<Product> findByName(String productName);
}
