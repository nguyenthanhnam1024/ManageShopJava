package com.example.manage_shops.controller;

import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ShopService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/shop")
public class ShopController {
    private final ShopService shopService;
    private final Commons commons;

    public ShopController(ShopService shopService, Commons commons) {
        this.shopService = shopService;
        this.commons = commons;
    }

    @GetMapping("/getShopList")
    public ResponseEntity<?> getAllShop() throws MyValidateException {
        return ResponseEntity.ok(shopService.getAllShop());
    }

    @GetMapping("/getById/{shopId}")
    public ResponseEntity<?> getShopById(HttpServletRequest httpServletRequest, @PathVariable int shopId) throws MyValidateException {
        return ResponseEntity.ok(shopService.getShopById(httpServletRequest, shopId));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveShop(HttpServletRequest httpServletRequest, @Valid @RequestBody Shop shop, BindingResult result) throws MyValidateException {
        Map<String, String> mapError = new HashMap<>();
        if (shop.getHotline() != null) {
            if (!shop.getHotline().matches("0\\d*")) {
                mapError.put("hotline", "hotline invalid");
            }
        }
        if (result.hasErrors() || !mapError.isEmpty()) {
            Map<String, String> mapBindingError = commons.handleExceptionInBindingResult(result);
            mapBindingError.putAll(mapError);
            return ResponseEntity.status(1000).body(mapBindingError);
        }
        return ResponseEntity.ok(shopService.saveShop(httpServletRequest, shop));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShop(HttpServletRequest httpServletRequest, @Valid @RequestBody Shop shop, BindingResult result) throws MyValidateException {
        Map<String, String> mapError = new HashMap<>();
        if (shop.getHotline() != null) {
            if (!shop.getHotline().matches("0\\d*")) {
                mapError.put("hotline", "hotline invalid");
            }
        }
        if (result.hasErrors() || !mapError.isEmpty()) {
            Map<String, String> mapBindingError = commons.handleExceptionInBindingResult(result);
            mapBindingError.putAll(mapError);
            return ResponseEntity.status(1000).body(mapBindingError);
        }
        return ResponseEntity.ok(shopService.updateShop(httpServletRequest, shop));
    }

    @DeleteMapping("/delete/{shopId}")
    public ResponseEntity<?> deleteShop(HttpServletRequest httpServletRequest, @PathVariable int shopId) throws MyValidateException{
        return ResponseEntity.ok(shopService.deleteShop(httpServletRequest, shopId));
    }

    @GetMapping ("/getByKeyword")
    public ResponseEntity<?> getShopByKeyword(HttpServletRequest httpServletRequest, @RequestParam String keyword) throws MyValidateException {
        return ResponseEntity.ok(shopService.getShopByKeyword(httpServletRequest, keyword));
    }
}
