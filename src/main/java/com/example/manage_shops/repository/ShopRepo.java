package com.example.manage_shops.repository;

import com.example.manage_shops.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShopRepo extends JpaRepository<Shop, Integer> {
    Optional<Shop> findByName(String shopName);

    List<Shop> findByNameContainingIgnoreCase(String keyword);
}
