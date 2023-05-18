package com.example.manage_shops.service_ipm;

import com.example.manage_shops.dto.RequestLogin;
import com.example.manage_shops.dto.ResponseLogin;
import com.example.manage_shops.entity.*;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.*;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.SecurityService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityServiceImp implements SecurityService {
    private final AccountRepo accountRepo;
    private final Commons commons;
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final RoleUserRepo roleUserRepo;
    private final ShopRepo shopRepo;

    @Override
    public List<Shop> getShopList() {
        return shopRepo.findAll();
    }

    @Override
    public String errorCheckAccountMap(RequestLogin requestLogin) {
        if (requestLogin.getUserName() == null ||requestLogin.getPassword() == null) {
            return "username or password not correct";
        }
        Optional<Account> accountOptional = accountRepo.findByUserName(requestLogin.getUserName());
        if (!accountOptional.isPresent()) {
            return "username or password not correct";
        }
        return null;
    }

    @Override
    @Transactional
    public void registerUser(User user, Account account) throws MyValidateException {
        Account accountExist = commons.checkAccountInDatabase(account);
        user.setIdAccount(accountExist.getId());
        commons.checkUserInDatabase(user);
        userRepo.save(user);
        accountRepo.save(account);
    }

    @Override
    public ResponseLogin responseLogin(String userName) {
        Account account = accountRepo.findByUserName(userName).get();
        User user = userRepo.findByIdAccount(account.getId()).get();
        Shop shop = shopRepo.findById(user.getIdShop()).get();
        RoleUser roleUser = roleUserRepo.findByIdUser(user.getId()).get();
        Role role = roleRepo.findById(roleUser.getIdRole()).get();
        ModelMapper modelMapper = new ModelMapper();
        ResponseLogin responseLogin = modelMapper.map(user, ResponseLogin.class);
        responseLogin.setShop(shop);
        responseLogin.setRole(role.getRoleName());
        return responseLogin;
    }
}
