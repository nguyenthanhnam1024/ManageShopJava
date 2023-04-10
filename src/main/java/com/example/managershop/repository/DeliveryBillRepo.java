package com.example.managershop.repository;

import com.example.managershop.entity.DeliveryBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryBillRepo extends JpaRepository<DeliveryBill, Long> {
}
