package com.example.manage_shops.repository;

import com.example.manage_shops.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {
    List<Product> findProductByIdShop(int idShop);
    Optional<Product> findByName(String name);
    List<Product> findByNameContainingIgnoreCase(String keyword);

    @Query("SELECT p FROM Product  p WHERE p.name = :name AND p.idShop = :idShop")
    Optional<Product> findByNameAndIdShop(@Param("name") String productName, @Param("idShop") int idShop);

    @Query("SELECT p FROM Product  p WHERE p.id = :id AND p.idShop = :idShop")
    Optional<Product> findByIdAndIdShop(@Param("id") long id, @Param("idShop") int idShop);
}
