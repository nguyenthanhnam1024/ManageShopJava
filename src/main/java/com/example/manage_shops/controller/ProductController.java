package com.example.manage_shops.controller;

import com.example.manage_shops.entity.Product;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final Commons commons;

    public ProductController(ProductService productService, Commons commons) {
        this.productService = productService;
        this.commons = commons;
    }

    @GetMapping("/getByIdShop/{idShop}")
    public ResponseEntity<?> getProductByIdShop(@PathVariable int idShop) throws MyValidateException {
        return ResponseEntity.ok(productService.getProductByIdShop(idShop));
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity<?> getProductByIdShop(@PathVariable Long id) throws MyValidateException {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveProduct(@Valid @RequestBody Product product, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(productService.saveProduct(product));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProduct(@Valid @RequestBody Product product, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(productService.updateProduct(product));
    }

    @DeleteMapping("/delete/{id}/{idShop}")
    public  ResponseEntity<?> deleteProduct(@PathVariable Long id, @PathVariable int idShop) throws MyValidateException {
        return ResponseEntity.ok(productService.deleteProduct(id, idShop));
    }
    
    @GetMapping("/searchProductByKeyword")
    public ResponseEntity<?> searchProductByKeyword(@RequestParam("keyword") String keyword) throws MyValidateException {
        return ResponseEntity.ok(productService.searchProductByKeyword(keyword));
    }
}
