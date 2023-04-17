package com.example.manage_shops.service;

import com.example.manage_shops.dto.ProductDTO;
import com.example.manage_shops.entity.Product;

import javax.xml.bind.ValidationException;
import java.util.List;

public interface ProductService {
    List<ProductDTO> getAllProductByIdShop(int idShop);

    String validateRoleAdminAndManege(int roleId);

    String saveProduct(Product product);

    String deleteProduct(Long id);

    List<ProductDTO> searchProductByKeyword(String keyword);

    ProductDTO mapIntoProductDTO(Product product);

    List<ProductDTO> mapListProductCrossListProductDTO(List<Product> listProduct);
}
