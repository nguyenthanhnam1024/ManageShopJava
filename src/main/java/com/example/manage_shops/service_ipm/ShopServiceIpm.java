package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.repository.ShopRepo;
import com.example.manage_shops.service.ShopService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceIpm implements ShopService {
    private final ShopRepo shopRepo;

    public ShopServiceIpm(ShopRepo shopRepo) {
        this.shopRepo = shopRepo;
    }

    @Override
    public List<Shop> getAllShop() {
        return shopRepo.findAll();
    }

    @Override
    public void saveShop(Shop shop) {
        shopRepo.save(shop);
    }

    @Override
    public void updateShop(Shop shop) {
        shopRepo.save(shop);
    }

    @Override
    public void deleteShop(Long id) {
        shopRepo.deleteById(id);
    }

    @Override
    public List<Shop> searchShopByKeyword(String keyword) {
        return shopRepo.findByNameContainingIgnoreCase(keyword);
    }
}
