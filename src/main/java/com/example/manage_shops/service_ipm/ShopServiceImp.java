package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.ShopRepo;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ShopService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShopServiceImp implements ShopService {
    private final Commons commons;
    private  final ShopRepo shopRepo;

    @Override
    public List<Shop> getAllShop() throws MyValidateException {
        try {
            return shopRepo.findAll();
        } catch (Exception ex) {
            throw new MyValidateException("get list shop failure");
        }
    }

    @Override
    public Shop getShopById(HttpServletRequest httpServletRequest, int shopId) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
        Optional<Shop> shopExist = shopRepo.findById(shopId);
        if (shopExist.isPresent()) {
                return shopExist.get();
        }
        throw new MyValidateException("shop no exist");
    }

    @Override
    public Shop saveShop(HttpServletRequest httpServletRequest, Shop shop) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
        Optional<Shop> shopExist = shopRepo.findByName(shop.getName());
        if (!shopExist.isPresent()) {
            Optional<Shop> shopOptional = shopRepo.findByHotline(shop.getHotline());
            if (shopOptional.isPresent()) {
                throw new MyValidateException("hotline already exist");
            }
            try {
                return shopRepo.save(shop);
            } catch (Exception ex) {
                throw new MyValidateException("create shop failure");
            }
        }
        throw new MyValidateException("shop name already exist");
    }

    @Override
    public Shop updateShop(HttpServletRequest httpServletRequest, Shop shop) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
        Optional<Shop> shopExist = shopRepo.findById(shop.getId());
        if (shopExist.isPresent()) {
            if (!shopExist.get().getName().equals(shop.getName())) {
                Optional<Shop> shopOptional = shopRepo.findByName(shop.getName());
                if (shopOptional.isPresent()) {
                    throw new MyValidateException("shop already exist");
                }
            }
            if (!shop.getHotline().equals(shopExist.get().getHotline())) {
                Optional<Shop> shopOptional = shopRepo.findByHotline(shop.getHotline());
                if (shopOptional.isPresent()) {
                    throw new MyValidateException("hotline already exist");
                }
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
    public Shop deleteShop(HttpServletRequest httpServletRequest, int shopId) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
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
    public List<Shop> getShopByKeyword(HttpServletRequest httpServletRequest, String keyword) throws MyValidateException {
        if (keyword == null || keyword.equals("")) {
            throw new MyValidateException("keyword must be different null and blank");
        }
        commons.validateRoleForADMIN(httpServletRequest);
        try {
            return shopRepo.findByNameContainingIgnoreCase(keyword);
        } catch (Exception ex) {
            throw new MyValidateException("error as search");
        }
    }
}
