package com.example.manage_shops.service;

import com.example.manage_shops.dto.ProductDTO;
import com.example.manage_shops.entity.Product;
import org.springframework.http.ResponseEntity;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProductByIdShop(int idShop);

    String saveProduct(Product product) throws ValidationException;

    ResponseEntity<?> deleteProduct(Long id);

    List<ProductDTO> searchProductByKeyword(String keyword);

    ProductDTO mapIntoProductDTO(Product product);

    List<ProductDTO> mapListProductCrossListProductDTO(List<Product> listProduct);
}
