package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Product;
import com.example.manage_shops.repository.ProductRepo;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceIpm implements ProductService {
    private final ProductRepo productRepo;
    private final Commons commons;

    public ProductServiceIpm(ProductRepo productRepo, Commons commons) {
        this.productRepo = productRepo;
        this.commons = commons;
    }

    @Override
    public List<Product> getAllProductByIdShop(int idShop) {
        return productRepo.findAllByIdShop(idShop);
    }

    @Override
    public void saveProduct(Product product) {
        int[] listRoleId = {1, 2};
        int roleId = product.getIdShop();
        commons.validateRoleId(listRoleId, roleId);
    }
}
