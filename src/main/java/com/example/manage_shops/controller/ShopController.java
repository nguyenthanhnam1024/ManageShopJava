package com.example.manage_shops.controller;

import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;
    private final Commons commons;

    public ShopController(ShopService shopService, Commons commons) {
        this.shopService = shopService;
        this.commons = commons;
    }

    @GetMapping("/getAll/{roleIdUser}")
    public ResponseEntity<?> getAllShop(@PathVariable int roleIdUser) throws MyValidateException {
        return ResponseEntity.ok(shopService.getAllShop(roleIdUser));
    }

    @GetMapping("/getById/{shopId}/{roleIdUser}")
    public ResponseEntity<?> getShopById(@PathVariable int shopId, @PathVariable int roleIdUser) throws MyValidateException {
        return ResponseEntity.ok(shopService.getShopById(shopId, roleIdUser));
    }

    @PostMapping("/save/{roleIdUser}")
    public ResponseEntity<?> saveShop(@Valid @RequestBody Shop shop, @PathVariable int roleIdUser, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(shopService.saveShop(shop, roleIdUser));
    }

    @PutMapping("/update/{roleIdUser}")
    public ResponseEntity<?> updateShop(@Valid @RequestBody Shop shop, @PathVariable int roleIdUser, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(shopService.updateShop(shop, roleIdUser));
    }

    @DeleteMapping("/delete/{shopId}/{roleIdUser}")
    public ResponseEntity<?> deleteRole(@PathVariable int shopId, @PathVariable int roleIdUser) throws MyValidateException{
        return ResponseEntity.ok(shopService.deleteShop(shopId, roleIdUser));
    }

    @GetMapping("/getByKeyword/{roleIdUser}")
    public ResponseEntity<?> getShopByKeyword(@PathVariable int roleIdUser, @RequestParam String keyword) throws MyValidateException {
        return ResponseEntity.ok(shopService.getShopByKeyword(keyword, roleIdUser));
    }
}
