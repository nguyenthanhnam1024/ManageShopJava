package com.example.manage_shops.service;

import com.example.manage_shops.dto.ProductDTO;
import com.example.manage_shops.entity.Product;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ProductService {
    List<Product> getAllProductByIdShop(int idShop);

    ResponseEntity<?> saveProduct(Product product) throws ValidationException;

    ProductDTO mapIntoProductDTO(Product product);
}
