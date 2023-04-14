package com.example.manage_shops.repository;

import com.example.manage_shops.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepo extends JpaRepository<Receipt, Long> {
}
