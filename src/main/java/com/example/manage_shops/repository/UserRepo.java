package com.example.manage_shops.repository;

import com.example.manage_shops.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long>, CustomUserRepo {
    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword%")
    List<User> searchUserByKeyword(@Param("keyword") String keyword);

    @Query("SELECT u\n" +
            "FROM User u\n" +
            "JOIN RoleUser ru ON u.id = ru.idUser\n" +
            "JOIN Role r ON r.id = ru.idRole\n" +
            "WHERE u.name LIKE %:keyword%\n" +
            "AND r.roleName = :roleName")
    List<User> searchUserByKeywordAndRole(@Param("keyword") String keyword, @Param("roleName") String roleName);

    @Query("SELECT u\n" +
            "FROM User u\n" +
            "JOIN RoleUser ru ON u.id = ru.idUser\n" +
            "JOIN Role r ON r.id = ru.idRole\n" +
            "WHERE r.roleName = :roleName")
    List<User> searchUserByRole(@Param("roleName") String roleName);

    @Query("SELECT u FROM User u WHERE u.name LIKE %:keyword% AND u.idShop = :idShop")
    List<User> searchUserByKeywordAndIdShop(@Param("keyword") String keyword, @Param("idShop") int idShop);

    Optional<User> findByIdAccount(Long accountId);

    Optional<User> findByName(String name);

    void deleteByName(String name);

    List<User> findByIdShop(int idShop);
}