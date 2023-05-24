package com.example.manage_shops.repository;

import com.example.manage_shops.entity.DeliveryBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryBillRepo extends JpaRepository<DeliveryBill, Long> {
}
