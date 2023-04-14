package com.example.manage_shops.controller;

import com.example.manage_shops.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAllByIdShop/{idShop}")
    public ResponseEntity<?> getAllProductByIdShop(@PathVariable int idShop) {
        return ResponseEntity.ok(productService.getAllProductByIdShop(idShop));
    }
}
