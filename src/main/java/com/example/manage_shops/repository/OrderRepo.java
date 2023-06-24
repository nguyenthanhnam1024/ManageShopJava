package com.example.manage_shops.repository;

import com.example.manage_shops.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
    List<Order> findAllByIdShop(int idShop);
    List<Order> findAllByDate(LocalDate date);
}
