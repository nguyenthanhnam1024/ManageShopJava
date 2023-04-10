package com.example.managershop.service_Imp;

import com.example.managershop.entity.Product;
import com.example.managershop.repository.OrderRepo;
import com.example.managershop.repository.ProductRepo;
import com.example.managershop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceIpm implements ProductService {

    @Autowired
    ProductRepo productRepo;
    @Autowired
    OrderRepo orderRepo;

    @Override
    public List<Product> getAllProduct() {
        List<Product> listProduct = productRepo.findAll();
        return listProduct;
    }

    @Override
    public void saveProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public void updateProduct(Product product) {
        productRepo.save(product);
    }

    @Override
    public void deleteProductById(Long id) {
        productRepo.deleteById(id);
    }

}
