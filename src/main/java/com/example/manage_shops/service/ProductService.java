package com.example.manage_shops.service;

import com.example.manage_shops.entity.Product;
import com.example.manage_shops.exception.MyValidateException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface ProductService {
    List<Product> getProductByIdShop(HttpServletRequest httpServletRequest, int idShop) throws MyValidateException;

    Product getProductById(HttpServletRequest httpServletRequest, Long idProduct, int idShop) throws MyValidateException;

    Product saveProduct(HttpServletRequest httpServletRequest, int idShop, Product product) throws MyValidateException;

    Product updateProduct(HttpServletRequest httpServletRequest, int idShop, Product product) throws MyValidateException;

    void deleteProduct(HttpServletRequest httpServletRequest, int idShop, Long idProduct) throws MyValidateException;

    List<Product> searchProductByKeyword(HttpServletRequest httpServletRequest, int idShop, String keyword) throws MyValidateException;
}
