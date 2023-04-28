package com.example.manage_shops.service_ipm;

import com.example.manage_shops.dto.ProductDTO;
import com.example.manage_shops.entity.Product;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.ProductRepo;
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

    @Autowired
    public ProductServiceIpm(ProductRepo productRepo, Commons commons) {
        this.productRepo = productRepo;
        this.commons = commons;
    }

    @Override
    public List<ProductDTO> getProductByIdShop(int idShop) throws MyValidateException {
        List<Product> productList = productRepo.findProductByIdShop(idShop);
        if (productList == null) {
            throw new MyValidateException("shop no have product exits");
        }
        Collections.reverse(productList);
        return this.mapListProductCrossListProductDTO(productList);
    }

    @Override
    public ProductDTO getProductById(Long id) throws MyValidateException {
        Optional<Product> product = productRepo.findById(id);
        if (product.isPresent()) {
            return this.mapIntoProductDTO(product.get());
        }
        throw  new MyValidateException("undefine this product");
    }

    @Override
    public String validateRoleAdminAndManege(int roleId) {
        List<Integer> listRoleId = new ArrayList<>();
        listRoleId.add(1);
        listRoleId.add(2);
        return commons.validateRoleId(listRoleId, roleId);
    }

    @Override
    public ProductDTO saveProduct(Product product) throws MyValidateException {
        String message = this.validateRoleAdminAndManege(product.getIdShop());
        if (message == null) {
            Product productExist = productRepo.findProductByName(product.getName());
            if (productExist != null) {
                    throw  new MyValidateException("shop have been product '"+productExist.getName()+"'");
            }
            return this.mapIntoProductDTO(productRepo.save(product));
        }
        throw  new MyValidateException(message);
    }

    @Override
    public ProductDTO updateProduct(Product product) throws MyValidateException {
        String message = this.validateRoleAdminAndManege(product.getIdShop());
        if (message == null) {
            Optional<Product> opProduct = productRepo.findById(product.getId());
            if (!opProduct.isPresent()) {
                throw  new MyValidateException("shop have been this product");
            }
            return this.mapIntoProductDTO(productRepo.save(product));
        }
        throw  new MyValidateException(message);
    }

    @Override
    public ProductDTO deleteProduct(Long id, int idShop) throws MyValidateException {
        String message = this.validateRoleAdminAndManege(idShop);
        if (message == null) {
            Optional<Product> opProduct = productRepo.findById(id);
            if (opProduct.isPresent()) {
                try {
                    productRepo.deleteById(id);
                    return this.mapIntoProductDTO(opProduct.get());
                } catch (Exception ex) {
                    throw  new MyValidateException("can not delete. Error server");
                }
            }
            throw  new MyValidateException("product no exist in database");
        }
        throw  new MyValidateException(message);
    }

    @Override
    public List<ProductDTO> searchProductByKeyword(String keyword) throws MyValidateException {
        List<Product> productList = productRepo.findByNameContainingIgnoreCase(keyword);
        if (productList == null) {
            throw new MyValidateException("shop no have product name : "+keyword);
        }
        return this.mapListProductCrossListProductDTO(productList);
    }

    @Override
    public ProductDTO mapIntoProductDTO(Product productRequest) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(productRequest, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> mapListProductCrossListProductDTO(List<Product> listProduct) {
        return listProduct.stream().map(this::mapIntoProductDTO).collect(Collectors.toList());
    }
}
