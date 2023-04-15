package com.example.manage_shops.controller;

import com.example.manage_shops.entity.Product;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.bind.ValidationException;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final Commons commons;

    public ProductController(ProductService productService, Commons commons) {
        this.productService = productService;
        this.commons = commons;
    }

    @GetMapping("/getAllByIdShop/{idShop}")
    public ResponseEntity<?> getAllProductByIdShop(@PathVariable int idShop) {
        return ResponseEntity.ok(productService.getAllProductByIdShop(idShop));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product, BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, BindingResult result) throws ValidationException {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(productService.saveProduct(product));
    }
}
