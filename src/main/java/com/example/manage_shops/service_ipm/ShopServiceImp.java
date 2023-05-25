package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.ShopRepo;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopServiceImp implements ShopService {
    private final Commons commons;
    private  final ShopRepo shopRepo;

    @Override
    public void validateRole(String roleName) throws MyValidateException {
        if (roleName == null || roleName.equals("")) {
            throw new MyValidateException("you do not have permission to perform this function");
        }
        commons.validateRoleForADMIN(roleName);
    }

    @Override
    public List<Shop> getAllShop(ResponseLogin responseLogin) throws MyValidateException {
        this.validateRole(responseLogin.getRole());
            try {
                return shopRepo.findAll();
            } catch (Exception ex) {
                throw new MyValidateException("get list shop failure");
            }
    }

    @Override
    public Shop getShopById(int shopId, ResponseLogin responseLogin) throws MyValidateException {
        this.validateRole(responseLogin.getRole());
        Optional<Shop> shopExist = shopRepo.findById(shopId);
        if (shopExist.isPresent()) {
                return shopExist.get();
        }
        throw new MyValidateException("shop no exist");
    }

    @Override
    public Shop saveShop(Shop shop, ResponseLogin responseLogin) throws MyValidateException {
        this.validateRole(responseLogin.getRole());
        Optional<Shop> shopExist = shopRepo.findByName(shop.getName());
        if (!shopExist.isPresent()) {
            try {
                return shopRepo.save(shop);
            } catch (Exception ex) {
                throw new MyValidateException("create shop failure");
            }
        }
        throw new MyValidateException("shop have been exist");
    }

    @Override
    public Shop updateShop(Shop shop, ResponseLogin responseLogin) throws MyValidateException {
        this.validateRole(responseLogin.getRole());
        Optional<Shop> shopExist = shopRepo.findById(shop.getId());
        if (shopExist.isPresent()) {
            if (shopExist.get().getName().equals(shop.getName())) {
                throw new MyValidateException("shop have been exist");
            }
            try {
                return shopRepo.save(shop);
            } catch (Exception ex) {
                throw new MyValidateException("update shop failure");
            }
        }
        throw new MyValidateException("can't found this shop in database to update");
    }

    @Override
    public Shop deleteShop(int shopId, ResponseLogin responseLogin) throws MyValidateException {
        this.validateRole(responseLogin.getRole());
        Optional<Shop> optionalShop = shopRepo.findById(shopId);
        if (!optionalShop.isPresent()) {
            throw new MyValidateException("can't found this shop to delete");
        }
        try {
            shopRepo.deleteById(shopId);
            return optionalShop.get();
        } catch (Exception ex) {
            throw new MyValidateException("error as delete shop");
        }
    }

    @Override
    public List<Shop> getShopByKeyword(String keyword, ResponseLogin responseLogin) throws MyValidateException {
        if (keyword == null || keyword.equals("")) {
            throw new MyValidateException("keyword must be different null and blank");
        }
        this.validateRole(responseLogin.getRole());
        try {
            return shopRepo.findByNameContainingIgnoreCase(keyword);
        } catch (Exception ex) {
            throw new MyValidateException("error as search");
        }
    }
}
