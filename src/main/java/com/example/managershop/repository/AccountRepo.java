package com.example.managershop.repository;

import com.example.managershop.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    @Query("SELECT a FROM Account a WHERE a.userName LIKE %:keyword%")
    List<Account> searchAccountByKeyword(@Param("keyword") String keyword);
}
