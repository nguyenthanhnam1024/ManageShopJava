package com.example.manage_shops.controller;

import com.example.manage_shops.entity.Product;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    public ResponseEntity<?> getProductByIdShop(HttpServletRequest httpServletRequest, @PathVariable int idShop) throws MyValidateException {
        return ResponseEntity.ok(productService.getProductByIdShop(httpServletRequest, idShop));
    }

    @GetMapping("/getById/{idProduct}/{idShop}")
    public ResponseEntity<?> getProductByIdShop(HttpServletRequest httpServletRequest, @PathVariable Long idProduct, @PathVariable int idShop) throws MyValidateException {
        return ResponseEntity.ok(productService.getProductById(httpServletRequest, idProduct, idShop));
    }

    @PostMapping("/save/{idShop}")
    public ResponseEntity<?> saveProduct(HttpServletRequest httpServletRequest, @PathVariable int idShop, @Valid @RequestBody Product product, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(productService.saveProduct(httpServletRequest, idShop, product));
    }

    @PutMapping("/update/{idShop}")
    public ResponseEntity<?> updateProduct(HttpServletRequest httpServletRequest, @PathVariable int idShop, @Valid @RequestBody Product product, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(productService.updateProduct(httpServletRequest, idShop, product));
    }

    @DeleteMapping("/delete/{idShop}/{idProduct}")
    public  ResponseEntity<?> deleteProduct(HttpServletRequest httpServletRequest, @PathVariable int idShop, @PathVariable Long idProduct) throws MyValidateException {
        productService.deleteProduct(httpServletRequest, idShop, idProduct);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/searchProductByKeyword/{idShop}")
    public ResponseEntity<?> searchProductByKeyword(HttpServletRequest httpServletRequest, @PathVariable int idShop, @RequestParam String keyword) throws MyValidateException {
        return ResponseEntity.ok(productService.searchProductByKeyword(httpServletRequest,idShop, keyword));
    }
}
