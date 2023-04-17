package com.example.manage_shops.service_ipm;

import com.example.manage_shops.dto.ProductDTO;
import com.example.manage_shops.entity.Product;
import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.repository.ProductRepo;
import com.example.manage_shops.repository.ShopRepo;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductServiceIpm implements ProductService {
    private final ProductRepo productRepo;
    private final Commons commons;
    private final ShopRepo shopRepo;

    @Autowired
    public ProductServiceIpm(ProductRepo productRepo, Commons commons, ShopRepo shopRepo) {
        this.productRepo = productRepo;
        this.commons = commons;
        this.shopRepo = shopRepo;
    }

    @Override
    public List<ProductDTO> getProductByIdShop(int idShop) {
        List<Product> productList = productRepo.findProductByIdShop(idShop);
        if (productList == null) {
            return null;
        }
        Collections.reverse(productList);
        return this.mapListProductCrossListProductDTO(productList);
    }

    @Override
    public String validateRoleAdminAndManege(int roleId) {
        List<Integer> listRoleId = new ArrayList<>();
        listRoleId.add(1);
        listRoleId.add(2);
        return commons.validateRoleId(listRoleId, roleId);
    }

    @Override
    public String saveProduct(Product product) {
        Product productExist = productRepo.findProductByName(product.getName());
        if (productExist != null) {
            if (productExist.getName().equals(product.getName()) && productExist.getIdShop() == product.getIdShop()) {
                return "have been product name in shop:"+productExist.getName();
            }
            if (product.getPrice() <= 0) {
                return "price must > 0";
            }
        }
        String message = this.validateRoleAdminAndManege(product.getIdShop());
        if (message == null) {
            productRepo.save(product);
        }
        return message;
    }

    @Override
    public String deleteProduct(Long id) {
        Optional<Product> opProduct = productRepo.findById(id);
        if (opProduct.isPresent()) {
            productRepo.deleteById(id);
            return null;
        }
        return "product no exist in database";
    }

    @Override
    public List<ProductDTO> searchProductByKeyword(String keyword) {
        List<Product> productList = productRepo.findByNameContainingIgnoreCase(keyword);
        if (productList == null) {
            return null;
        }
        return this.mapListProductCrossListProductDTO(productList);
    }

    @Override
    public ProductDTO mapIntoProductDTO(Product productRequest) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Shop> opShop = shopRepo.findById(productRequest.getIdShop());
        Shop shop = new Shop();
        if (opShop.isPresent()) {
            shop = opShop.get();
        }
        ProductDTO productDTO = modelMapper.map(productRequest, ProductDTO.class);
        productDTO.setShop(shop);
        return productDTO;
    }

    @Override
    public List<ProductDTO> mapListProductCrossListProductDTO(List<Product> listProduct) {
        return listProduct.stream().map(this::mapIntoProductDTO).collect(Collectors.toList());
    }
}
