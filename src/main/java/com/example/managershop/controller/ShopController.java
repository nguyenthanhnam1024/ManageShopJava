package com.example.managershop.controller;

import com.example.managershop.entity.Shop;
import com.example.managershop.service.ShopService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }

    @GetMapping("/getAllShop")
    public List<Shop> getAllShop() {
        return shopService.getAllShop();
    }

    @PostMapping("/saveShop")
    public void saveShop(@Valid @RequestBody Shop shop) {
        shopService.saveShop(shop);
    }

    @PutMapping("/updateShop")
    public void updateShop(@Valid @RequestBody Shop shop) {
        shopService.updateShop(shop);
    }

    @DeleteMapping("/deleteShop/{id}")
    public void deleteShop(@PathVariable Long id) {
        shopService.deleteShop(id);
    }

    @GetMapping("/searchShopByKeyword")
    public List<Shop> searchShopByKeyword(@RequestParam("keyword") String keyword) {
        return shopService.searchShopByKeyword(keyword);
    }
}
