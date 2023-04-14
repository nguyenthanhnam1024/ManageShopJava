package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Product;
import com.example.manage_shops.repository.ProductRepo;
import com.example.manage_shops.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceIpm implements ProductService {
    private final ProductRepo productRepo;

    public ProductServiceIpm(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public List<Product> getAllProductByIdShop(int idShop) {
        return productRepo.findAllByIdShop(idShop);
    }
}
