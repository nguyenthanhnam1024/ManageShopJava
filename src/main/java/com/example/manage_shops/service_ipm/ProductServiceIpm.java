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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.xml.bind.ValidationException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
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
    public List<ProductDTO> getAllProductByIdShop(int idShop) {
        return this.mapListProductCrossListProductDTO(productRepo.findAllByIdShop(idShop));
    }

    @Override
    public String saveProduct(Product product) throws ValidationException {
        Product product1 = productRepo.findProductByName(product.getName());
        if (product1.getName().equals(product.getName()) && product1.getIdShop() == product.getIdShop()) {
            return "have been product name in shop:"+product1.getName();
        }
        if (product.getPrice() <= 0) {
            return "price must > 0";
        }
        List<Integer> listRoleId = new ArrayList<>();
        listRoleId.add(1);
        listRoleId.add(2);
        int roleId = product.getIdShop();
        commons.validateRoleId(listRoleId, roleId);
        return null;
    }

    @Override
    public ResponseEntity<?> deleteProduct(Long id) {
        Optional<Product> opProduct = productRepo.findById(id);
        if (opProduct.isPresent()) {
            productRepo.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @Override
    public List<ProductDTO> searchProductByKeyword(String keyword) {
        return this.mapListProductCrossListProductDTO(productRepo.findByNameContainingIgnoreCase(keyword));
    }

    public ProductDTO mapIntoProductDTO(Product product) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.typeMap(Product.class, ProductDTO.class)
                .setConverter(MappingContext -> {
                    Product product1 = MappingContext.getSource();
                    ProductDTO productDTO = MappingContext.getDestination();
                    Shop shop = shopRepo.findById(product1.getIdShop())
                            .orElseThrow(() -> new NoSuchElementException("unregister shop for product"));
                    productDTO.setShop(shop);
                    return productDTO;
                });
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> mapListProductCrossListProductDTO(List<Product> listProduct) {
        return listProduct.stream().map(this::mapIntoProductDTO).collect(Collectors.toList());
    }
}
