package com.example.managershop.repository;

import com.example.managershop.entity.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptRepo extends JpaRepository<Receipt, Long> {
}
