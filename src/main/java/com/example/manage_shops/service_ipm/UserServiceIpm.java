package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.*;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.jwt.ExtractDataFromJwt;
import com.example.manage_shops.my_enum.RoleEnum;
import com.example.manage_shops.repository.*;
import com.example.manage_shops.repository.hql.UserRepository;
import com.example.manage_shops.request.RequestUpdateUser;
import com.example.manage_shops.request.RequestUser;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceIpm implements UserService {
    private final UserRepo userRepo;
    private final AccountRepo accountRepo;
    private final RoleRepo roleRepo;
    private final RoleUserRepo roleUserRepo;
    private final ShopRepo shopRepo;
    private final Commons commons;
    private final ExtractDataFromJwt extractDataFromJwt;
    private final UserRepository userRepository;

    @Override
    public List<User> getAllUser(HttpServletRequest httpServletRequest, int idShop) throws MyValidateException {
        List<String> listRoles = extractDataFromJwt.extractRoleNamesFromJwt(httpServletRequest);
         for (String roleName : listRoles) {
            if (RoleEnum.ADMIN.getRoleName().equals(roleName)) {
                if (idShop == 0) {
                    try {
                        return userRepo.findAll();
                    } catch (Exception ex) {
                        throw new MyValidateException("get list user failure");
                    }
                }
                 else {
                    try {
                        return userRepo.findByIdShop(idShop);
                    } catch (Exception ex) {
                        throw new MyValidateException("get list user failure");
                    }
                }
            }
             if (RoleEnum.MANAGE.getRoleName().equals(roleName) || RoleEnum.STAFF.getRoleName().equals(roleName) && idShop == extractDataFromJwt.extractIdShopFromJwt(httpServletRequest)) {
                 try {
                     return userRepo.findByIdShop(extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
                 } catch (Exception ex) {
                     throw new MyValidateException("get list user failure");
                 }
             }
        }
        throw new MyValidateException("you do not have permission to perform this function");
    }

    @Override
    public RequestUser getById(HttpServletRequest httpServletRequest, long idUser) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
        Optional<User> userOptional = userRepo.findById(idUser);
        if (userOptional.isPresent()) {
            RequestUser requestUser = new RequestUser();
            Optional<Shop> shopOptional = shopRepo.findById(userOptional.get().getIdShop());
            shopOptional.ifPresent(shop -> requestUser.setIdShop(shop.getId()));
            Optional<RoleUser> roleUserOptional = roleUserRepo.findByIdUser(userOptional.get().getId());
            if (roleUserOptional.isPresent()) {
                Optional<Role> roleOptional = roleRepo.findById(roleUserOptional.get().getIdRole());
                roleOptional.ifPresent(role -> requestUser.setRoleName(role.getRoleName()));
            }
            requestUser.setName(userOptional.get().getName());
            requestUser.setEmail(userOptional.get().getEmail());
            return requestUser;
        }
        throw new MyValidateException("cant not update");
    }

    @Override
    @Transactional
    public void saveUserFromADMIN(HttpServletRequest httpServletRequest, RequestUser requestUser) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
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
    @Transactional
    public ResponseLogin updateUser(HttpServletRequest httpServletRequest, RequestUpdateUser requestUpdateUser) throws MyValidateException {
        Optional<Shop> shopOptional = shopRepo.findById(requestUpdateUser.getShop().getId());
        int idShopFromJwt = extractDataFromJwt.extractIdShopFromJwt(httpServletRequest);
        if (shopOptional.isPresent() && idShopFromJwt  != 0 || !shopOptional.isPresent() && idShopFromJwt == 0) {
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
        throw new MyValidateException("Your shop as register does not exist");
    }

    @Override
    @Transactional
    public User updateUserFromADMIN(HttpServletRequest httpServletRequest, RequestUser requestUser) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
        Optional<Shop> shopOptional = shopRepo.findById(requestUser.getIdShop());
        if (shopOptional.isPresent()) {
            Optional<Role> roleOptional = roleRepo.findByRoleName(requestUser.getRoleName());
            if (roleOptional.isPresent()) {
                Optional<User> userOptional = userRepo.findByName(requestUser.getName());
                if (userOptional.isPresent()) {
                    User user = userOptional.get();
                    user.setIdShop(requestUser.getIdShop());
                    roleUserRepo.deleteByIdUser(user.getId());
                    RoleUser roleUser = new RoleUser();
                    roleUser.setIdRole(roleOptional.get().getId());
                    roleUser.setIdUser(user.getId());
                    try {
                        roleUserRepo.save(roleUser);
                        return userRepo.save(user);
                    } catch (Exception ex) {
                        throw new MyValidateException("can not update, error server");
                    }
                }
                throw new MyValidateException("not found user for update");
            }
            throw new MyValidateException("not found role for update");
        }
        throw new MyValidateException("not found shop for update");
    }

    @Override
    public List<User> searchUserByKeyword(HttpServletRequest httpServletRequest, String keyword, String roleName) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
        List<String> roles = extractDataFromJwt.extractRoleNamesFromJwt(httpServletRequest);
        for (String roleNameFromJwt : roles) {
            if (RoleEnum.ADMIN.getRoleName().equals(roleNameFromJwt)) {
                try {
                    if (roleName !=null && !roleName.equals("")) {
                        if (keyword == null || keyword.equals("")) {
                            return userRepo.searchUserByRole(roleName);
                        }
                        return userRepo.searchUserByKeywordAndRole(keyword, roleName);
                    }
                    return userRepo.searchUserByKeyword(keyword);
                } catch (Exception ex) {
                    throw new MyValidateException("get list user failure");
                }
            }
            if (RoleEnum.MANAGE.getRoleName().equals(roleNameFromJwt)) {
                try {
                    return userRepo.searchUserByKeywordAndIdShop(keyword, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
                } catch (Exception ex) {
                    throw new MyValidateException("get list user failure");
                }
            }
            throw new MyValidateException("you do not have permission to perform this function");
        }
        return null;
    }

    @Override
    public List<User> searchUserByHQL(HttpServletRequest httpServletRequest, String keyword, String roleName) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
        List<String> roles = extractDataFromJwt.extractRoleNamesFromJwt(httpServletRequest);
        for (String roleNameFromJwt : roles) {
            if (RoleEnum.ADMIN.getRoleName().equals(roleNameFromJwt) || RoleEnum.MANAGE.getRoleName().equals(roleNameFromJwt)) {
                return userRepository.getUser(keyword, roleName, extractDataFromJwt.extractIdShopFromJwt(httpServletRequest));
            }

        }
        throw new MyValidateException("you do not have permission to perform this function");
    }

    @Override
    @Transactional
    public void deleteUser(HttpServletRequest httpServletRequest, Long idUser) throws MyValidateException {
        commons.validateRole(httpServletRequest, Arrays.asList(RoleEnum.ADMIN.getRoleName(), RoleEnum.MANAGE.getRoleName()));
        try {
            Optional<User> userOptional = userRepo.findById(idUser);
            if (userOptional.isPresent()) {
                userRepo.deleteById(idUser);
                accountRepo.deleteById(userOptional.get().getIdAccount());
                roleUserRepo.deleteByIdUser(userOptional.get().getId());
            }
        } catch (Exception ex) {
            throw new MyValidateException("delete user failure");
        }
    }

}
