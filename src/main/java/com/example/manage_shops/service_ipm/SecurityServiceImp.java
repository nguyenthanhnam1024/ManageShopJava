package com.example.manage_shops.service_ipm;

import com.example.manage_shops.dto.RequestLogin;
import com.example.manage_shops.dto.RequestRegister;
import com.example.manage_shops.dto.ResponseLogin;
import com.example.manage_shops.entity.*;
import com.example.manage_shops.repository.*;
import com.example.manage_shops.service.SecurityService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class SecurityServiceImp implements SecurityService {
    private final AccountRepo accountRepo;
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
        } else {
            BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
            if (!bc.matches(requestLogin.getPassword(), accountOptional.get().getPassword())) {
                return "username or password not correct";
            } else {
                Optional<User> user = userRepo.findByIdAccount(accountOptional.get().getId());
                if (!user.isPresent()) {
                    return "account error";
                }
                Optional<RoleUser> roleUser = roleUserRepo.findByIdUser(user.get().getId());
                if (!roleUser.isPresent()) {
                    return "this account no authorization";
                }
                Optional<Role> role = roleRepo.findById(roleUser.get().getIdRole());
                if (!role.isPresent()) {
                    return "this account no authorization";
                }
            }
        }
        return null;
    }

    @Override
    public Map<String, String> errorCheckRequestRegisterMap(RequestRegister requestRegister) {
        Map<String, String> errorMap = new HashMap<>();
        if (requestRegister.getAge() <= 0 || requestRegister.getAge() > 150) {
            errorMap.put("age", "0 < age < 150");
        }
        if (accountRepo.findByUserName(requestRegister.getUserName()).isPresent()) {
            errorMap.put("userNames", "Account is present");
        }
        if (userRepo.findByName(requestRegister.getName()).isPresent()) {
            errorMap.put("names", "user name is present");
        }
        if (!shopRepo.findById(requestRegister.getIdShop()).isPresent()) {
            errorMap.put("idShops", "shop non present");
        }
        return errorMap;
    }

    @Override
    @Transactional
    public void registerUser(RequestRegister requestRegister) {
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        Account account = new Account();
        account.setId(null);
        account.setUserName(requestRegister.getUserName());
        account.setPassword(bc.encode(requestRegister.getPassword()));
        Account accountExist = accountRepo.save(account);
        User user = new User();
        user.setId(null);
        user.setIdShop(requestRegister.getIdShop());
        user.setIdAccount(accountExist.getId());
        user.setName(requestRegister.getName());
        user.setAge(requestRegister.getAge());
        user.setEmail(requestRegister.getEmail());
        user.setPhoneNumber(requestRegister.getPhoneNumber());
        user.setAddress(requestRegister.getAddress());
        User userExist = userRepo.save(user);
        RoleUser roleUser = new RoleUser();
        Optional<Role> roleExistPresent = roleRepo.findByRoleName("STAFF");
        if (roleExistPresent.isPresent()) {
            roleUser.setIdRole(roleExistPresent.get().getId());
        } else {
            Role role = new Role();
            role.setRoleName("STAFF");
            roleUser.setIdRole(roleRepo.save(role).getId());
        }
        roleUser.setIdUser(userExist.getId());
        roleUserRepo.save(roleUser);
    }

    @Override
    public ResponseLogin responseLogin(String userName) {
        Account account = accountRepo.findByUserName(userName).get();
        User user = userRepo.findByIdAccount(account.getId()).get();

        RoleUser roleUser = roleUserRepo.findByIdUser(user.getId()).get();
        Role role = roleRepo.findById(roleUser.getIdRole()).get();
        ModelMapper modelMapper = new ModelMapper();
        ResponseLogin responseLogin = modelMapper.map(user, ResponseLogin.class);
        if(role.getRoleName().equals("ADMIN")) {
            Shop shopNew = new Shop();
            shopNew.setName("Manage system shop");
            responseLogin.setShop(shopNew);
        } else {
            Shop shop = shopRepo.findById(user.getIdShop()).get();
            responseLogin.setShop(shop);
        }
        responseLogin.setRole(role.getRoleName());
        return responseLogin;
    }
}
