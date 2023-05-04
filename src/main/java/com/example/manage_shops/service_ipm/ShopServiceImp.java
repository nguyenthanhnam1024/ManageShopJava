package com.example.manage_shops.service_ipm;

import com.example.manage_shops.dto.ShopDTO;
import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.ShopRepo;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.ShopService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShopServiceImp implements ShopService {
    private final Commons commons;

    private  final ShopRepo shopRepo;

    public ShopServiceImp(Commons commons, ShopRepo shopRepo) {
        this.commons = commons;
        this.shopRepo = shopRepo;
    }

    @Override
    public List<ShopDTO> getAllShop(int roleIdOfUser) throws MyValidateException {
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            return this.mapIntoListShopDTO(shopRepo.findAll());
        }
        throw new MyValidateException(message);
    }

    @Override
    public ShopDTO getShopById(int shopId, int roleIdOfUser) throws MyValidateException {
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            Optional<Shop> shopExist = shopRepo.findById(shopId);
            if (shopExist.isPresent()) {
                return this.mapIntoShopDTO(shopExist.get());
            }
            throw new MyValidateException("shop name no have exist");
        }
        throw new MyValidateException(message);
    }

    @Override
    public ShopDTO saveShop(Shop shop, int roleIdOfUser) throws MyValidateException {
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            Optional<Shop> shopExist = shopRepo.findByName(shop.getName());
            if (!shopExist.isPresent()) {
                return this.mapIntoShopDTO(shopRepo.save(shop));
            }
            throw new MyValidateException("shop name have been exist");
        }
        throw new MyValidateException(message);
    }

    @Override
    public ShopDTO updateShop(Shop shop, int roleIdOfUser) throws MyValidateException {
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            Optional<Shop> shopExist = shopRepo.findById(shop.getId());
            if (shopExist.isPresent()) {
                return this.mapIntoShopDTO(shopRepo.save(shop));
            }
            throw new MyValidateException("can't found this shop in database to update");
        }
        throw new MyValidateException(message);
    }

    @Override
    public ShopDTO deleteShop(int shopId, int roleIdOfUser) throws MyValidateException {
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            Optional<Shop> optionalShop = shopRepo.findById(shopId);
            if (!optionalShop.isPresent()) {
                throw new MyValidateException("can't found this shop to delete");
            }
            shopRepo.deleteById(shopId);
            return this.mapIntoShopDTO(optionalShop.get());
        }
        throw new MyValidateException(message);
    }

    @Override
    public List<ShopDTO> getShopByKeyword(String keyword, int roleIdOfUser) throws MyValidateException {
        if (keyword == null || keyword.equals("")) {
            throw new MyValidateException("keyword must be difficult null and blank");
        }
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            return this.mapIntoListShopDTO(shopRepo.findByNameContainingIgnoreCase(keyword));
        }
        throw new MyValidateException(message);
    }

    @Override
    public ShopDTO mapIntoShopDTO(Shop shop) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(shop, ShopDTO.class);
    }

    @Override
    public List<ShopDTO> mapIntoListShopDTO(List<Shop> shopList) {
        return shopList.stream().map(this::mapIntoShopDTO).collect(Collectors.toList());
    }
}
