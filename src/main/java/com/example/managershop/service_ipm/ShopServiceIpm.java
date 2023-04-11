package com.example.managershop.service_ipm;

import com.example.managershop.entity.Shop;
import com.example.managershop.repository.ShopRepo;
import com.example.managershop.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceIpm implements ShopService {
    @Autowired
    private ShopRepo shopRepo;

    @Override
    public List<Shop> getAllShop() {
        List<Shop> listShop = shopRepo.findAll();
        return listShop;
    }

    @Override
    public void saveShop(Shop shop) {
        shopRepo.save(shop);
    }
}
