package com.example.manage_shops.repository;

import com.example.manage_shops.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword%")
    List<User> searchUserByKeyword(@Param("keyword") String keyword);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword% AND u.idShop = :idShop")
    List<User> searchUserByKeywordAndIdShop(@Param("keyword") String keyword, @Param("idShop") int idShop);

    Optional<User> findByIdAccount(Long accountId);

    Optional<User> findByName(String name);

    void deleteByName(String name);

    List<User> findByIdShop(int idShop);
}