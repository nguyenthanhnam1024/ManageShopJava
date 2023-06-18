package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Product;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.jwt.ExtractDataFromJwt;
import com.example.manage_shops.my_enum.RoleEnum;
import com.example.manage_shops.repository.ProductRepo;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductServiceImp implements ProductService {
    private final ProductRepo productRepo;
    private final Commons commons;
    private final ExtractDataFromJwt extractDataFromJwt;

    @Override
    public List<Product> getProductByIdShop(HttpServletRequest httpServletRequest, int idShop) throws MyValidateException {
        List<String> listRoleLicensed = Arrays.stream(RoleEnum.values()).map(RoleEnum::getRoleName).collect(Collectors.toList());
        commons.validateRole(httpServletRequest,listRoleLicensed);
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        try {
            return productRepo.findProductByIdShop(idShop);
        } catch (Exception ex) {
            throw new MyValidateException("request list product failure");
        }
    }

    @Override
    public Product getProductById(HttpServletRequest httpServletRequest, Long idProduct, int idShop) throws MyValidateException {
        commons.validateRole(httpServletRequest,Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName()));
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        Optional<Product> productOptional = productRepo.findById(idProduct);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new MyValidateException("cant not update product");
    }

    @Override
    @Transactional
    public Product saveProduct(HttpServletRequest httpServletRequest, int idShop, Product product) throws MyValidateException {
        commons.validateRole(httpServletRequest,Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName()));
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        Product productExist = productRepo.findProductByName(product.getName());
        if (productExist != null) {
            throw  new MyValidateException("shop have been product '"+productExist.getName()+"'");
        }
        return productRepo.save(product);
    }

    @Override
    @Transactional
    public Product updateProduct(HttpServletRequest httpServletRequest, int idShop, Product product) throws MyValidateException {
        commons.validateRole(httpServletRequest,Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName()));
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        Optional<Product> productOptional = productRepo.findById(product.getId());
        if (productOptional.isPresent()) {
            if (productOptional.get().getName().equals(product.getName())) {
                throw  new MyValidateException("shop have been product '"+product.getName()+"'");
            }
            return productRepo.save(product);
        }
        throw new MyValidateException("update product failure");
    }

    @Override
    @Transactional
    public void deleteProduct(HttpServletRequest httpServletRequest, int idShop, Long idProduct) throws MyValidateException {
        commons.validateRole(httpServletRequest,Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName()));
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        Optional<Product> productOptional = productRepo.findById(idProduct);
        if (productOptional.isPresent()) {
            productRepo.deleteById(idProduct);
        }
        throw new MyValidateException("delete product failure");
    }

    @Override
    public List<Product> searchProductByKeyword(HttpServletRequest httpServletRequest, int idShop, String keyword) throws MyValidateException {
        List<String> listRoleLicensed = Arrays.stream(RoleEnum.values()).map(RoleEnum::getRoleName).collect(Collectors.toList());
        commons.validateRole(httpServletRequest,listRoleLicensed);
        commons.validateShopForUserToQuery(idShop, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
        try {
            return productRepo.findByNameContainingIgnoreCase(keyword);
        } catch (Exception ex) {
            throw new MyValidateException("request list product failure");
        }
    }
}
