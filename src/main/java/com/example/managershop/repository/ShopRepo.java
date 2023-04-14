package com.example.managershop.repository;

import com.example.managershop.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepo extends JpaRepository<Shop, Integer> {
    List<Shop> findByNameContainingIgnoreCase(String keyword);
}
