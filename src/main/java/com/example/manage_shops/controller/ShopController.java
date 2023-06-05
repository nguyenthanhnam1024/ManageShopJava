package com.example.manage_shops.controller;

import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.request.RequestShop;
import com.example.manage_shops.response.ResponseLogin;
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

    @GetMapping("/getShopList")
    public ResponseEntity<?> getAllShop() throws MyValidateException {
        return ResponseEntity.ok(shopService.getAllShop());
    }

    @PostMapping("/getById/{shopId}")
    public ResponseEntity<?> getShopById(@PathVariable int shopId, @RequestBody ResponseLogin responseLogin) throws MyValidateException {
        return ResponseEntity.ok(shopService.getShopById(shopId, responseLogin));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveShop(@Valid @RequestBody RequestShop requestShop, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(shopService.saveShop(requestShop.getShop(), requestShop.getResponseLogin()));
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateShop(@Valid @RequestBody RequestShop requestShop, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(shopService.updateShop(requestShop.getShop(), requestShop.getResponseLogin()));
    }

    @DeleteMapping("/delete/{shopId}")
    public ResponseEntity<?> deleteRole(@PathVariable int shopId, @RequestBody ResponseLogin responseLogin) throws MyValidateException{
        return ResponseEntity.ok(shopService.deleteShop(shopId, responseLogin));
    }

    @PostMapping ("/getByKeyword")
    public ResponseEntity<?> getShopByKeyword(@RequestBody ResponseLogin responseLogin, @RequestParam String keyword) throws MyValidateException {
        return ResponseEntity.ok(shopService.getShopByKeyword(keyword, responseLogin));
    }
}
