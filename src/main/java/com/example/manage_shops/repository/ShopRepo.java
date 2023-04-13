package com.example.manage_shops.repository;

import com.example.manage_shops.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepo extends JpaRepository<Shop, Long> {
    List<Shop> findByNameContainingIgnoreCase(String keyword);
}
