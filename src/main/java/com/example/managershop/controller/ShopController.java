package com.example.managershop.controller;

import com.example.managershop.entity.Shop;
import com.example.managershop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping("/getAllShop")
    public List<Shop> getAllShop() {
        List<Shop> listShop = shopService.getAllShop();
        return listShop;
    }

    @PostMapping("/saveShop")
    public void saveShop(@Valid @RequestBody Shop shop) {
        shopService.saveShop(shop);
    }
}
