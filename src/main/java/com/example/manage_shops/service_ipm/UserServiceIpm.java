package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.*;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.my_enum.RoleEnum;
import com.example.manage_shops.repository.*;
import com.example.manage_shops.request.RequestRegister;
import com.example.manage_shops.request.RequestUpdateUser;
import com.example.manage_shops.request.RequestUser;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.SecurityService;
import com.example.manage_shops.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceIpm implements UserService {
    private final UserRepo userRepo;
    private final AccountRepo accountRepo;
    private final RoleRepo roleRepo;
    private final RoleUserRepo roleUserRepo;
    private final SecurityService securityService;
    private final ShopRepo shopRepo;

    @Override
    public List<User> getAllUser(ResponseLogin responseLogin) throws MyValidateException {
        if (RoleEnum.ADMIN.getRoleName().equals(responseLogin.getRole())) {
            try {
                return userRepo.findAll();
            } catch (Exception ex) {
                throw new MyValidateException("get list user failure");
            }
        }
        if (RoleEnum.MANAGE.getRoleName().equals(responseLogin.getRole())) {
            try {
                return userRepo.findByIdShop(responseLogin.getShop().getId());
            } catch (Exception ex) {
                throw new MyValidateException("get list user failure");
            }
        }
        throw new MyValidateException("you do not have permission to perform this function");
    }

    @Override
    public Map<String, String> errorCheckRequestRegisterFromADMINMap(RequestUser requestUser) {
        ModelMapper modelMapper = new ModelMapper();
        return securityService.errorCheckRequestRegisterMap(modelMapper.map(requestUser, RequestRegister.class));
    }

    @Override
    @Transactional
    public void saveUserFromADMIN(RequestUser requestUser) throws MyValidateException {
        if (!RoleEnum.ADMIN.getRoleName().equals(requestUser.getResponseLogin().getRole())){
            throw new MyValidateException("you do not have permission to perform this function");
        }
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        Account account = new Account();
        account.setUserName(requestUser.getUserName());
        account.setPassword(bc.encode(requestUser.getPassword()));
        Account accountExist = accountRepo.save(account);
        User user = new User();
        user.setIdShop(requestUser.getIdShop());
        user.setIdAccount(accountExist.getId());
        user.setName(requestUser.getName());
        user.setAge(requestUser.getAge());
        user.setEmail(requestUser.getEmail());
        user.setPhoneNumber(requestUser.getPhoneNumber());
        user.setAddress(requestUser.getAddress());
        User userExist = userRepo.save(user);
        Optional<Role> roleExistPresent = roleRepo.findByRoleName(requestUser.getRoleName());
        if (!roleExistPresent.isPresent()) {
            throw new MyValidateException("create user to role no exist");
        }
        RoleUser roleUser = new RoleUser();
        roleUser.setIdRole(roleExistPresent.get().getId());
        roleUser.setIdUser(userExist.getId());
        roleUserRepo.save(roleUser);
    }

    @Override
    public List<String> getListRoleName() {
        return roleRepo.findAll().stream().map(Role::getRoleName).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseLogin updateUser(RequestUpdateUser requestUpdateUser) throws MyValidateException {
        Optional<Shop> shopOptional = shopRepo.findById(requestUpdateUser.getShop().getId());
        if (shopOptional.isPresent()) {
            Optional<Account> accountOptional = accountRepo.findByUserName(requestUpdateUser.getUserNameOfAccount());
            if (accountOptional.isPresent()) {
                Optional<User> userOptional = userRepo.findByName(requestUpdateUser.getOldName());
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    ModelMapper modelMapper = new ModelMapper();
                    modelMapper.map(requestUpdateUser, user);
                    try {
                        userRepo.save(user);
                    } catch (Exception ex) {
                        throw new MyValidateException("can not update info. error server");
                    }
                    ResponseLogin responseLogin = modelMapper.map(requestUpdateUser, ResponseLogin.class);
                    modelMapper.map(user, responseLogin);
                    return responseLogin;
                } else {
                    throw new MyValidateException("Your info old does not exist to update");
                }
            } else {
                throw new MyValidateException("Your account does not exist");
            }
        }
        throw new MyValidateException("Your shop does not exist");
    }

    @Override
    public List<User> searchUserByKeyword(String keyword, ResponseLogin responseLogin) throws MyValidateException {
        if (RoleEnum.ADMIN.getRoleName().equals(responseLogin.getRole())) {
            try {
                return userRepo.searchUserByKeyword(keyword);
            } catch (Exception ex) {
                throw new MyValidateException("get list user failure");
            }
        }
        if (RoleEnum.MANAGE.getRoleName().equals(responseLogin.getRole())) {
            try {
                return userRepo.searchUserByKeywordAndIdShop(keyword, responseLogin.getShop().getId());
            } catch (Exception ex) {
                throw new MyValidateException("get list user failure");
            }
        }
        throw new MyValidateException("you do not have permission to perform this function");
    }
}
