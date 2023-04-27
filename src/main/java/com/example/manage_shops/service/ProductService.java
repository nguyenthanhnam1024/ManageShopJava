package com.example.manage_shops.service;

import com.example.manage_shops.dto.ProductDTO;
import com.example.manage_shops.entity.Product;
import com.example.manage_shops.exception.MyValidateException;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProductByIdShop(int idShop) throws MyValidateException;

    ProductDTO getProductById(Long id) throws MyValidateException;

    String validateRoleAdminAndManege(int roleId);

    ProductDTO saveProduct(Product product) throws MyValidateException;

    ProductDTO updateProduct(Product product) throws MyValidateException;

    ProductDTO deleteProduct(Long id, int idShop) throws MyValidateException;

    List<ProductDTO> searchProductByKeyword(String keyword) throws MyValidateException;

    ProductDTO mapIntoProductDTO(Product product);

    List<ProductDTO> mapListProductCrossListProductDTO(List<Product> listProduct);
}
