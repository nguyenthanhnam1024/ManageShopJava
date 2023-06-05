package com.example.manage_shops.repository;

import com.example.manage_shops.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
    Optional<Account> findByUserName(String userName);
    Optional<Account> findByPassword(String password);
    @Query("SELECT a FROM Account a WHERE a.userName LIKE %:keyword%")
    List<Account> searchAccountByKeyword(@Param("keyword") String keyword);
}
