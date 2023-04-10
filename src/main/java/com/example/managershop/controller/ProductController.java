package com.example.managershop.controller;

import com.example.managershop.entity.Product;
import com.example.managershop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/MS")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/getAllProduct")
    public ResponseEntity<?> getAllProduct() {
        List<Product> listProduct = productService.getAllProduct();
        return ResponseEntity.ok(listProduct);
    }

    @PostMapping("/saveProduct")
    public void saveProduct(@Valid @RequestBody Product product) {
        productService.saveProduct(product);
    }

    @PutMapping("/uadapeProduct")
    public void updateProduct(@Valid @RequestBody Product product) {
        productService.saveProduct(product);
    }

    @DeleteMapping("/deleteProduct/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProductById(id);
    }
}
