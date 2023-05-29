package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Account;
import com.example.manage_shops.entity.Role;
import com.example.manage_shops.entity.RoleUser;
import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.my_enum.RoleEnum;
import com.example.manage_shops.repository.AccountRepo;
import com.example.manage_shops.repository.RoleRepo;
import com.example.manage_shops.repository.RoleUserRepo;
import com.example.manage_shops.repository.UserRepo;
import com.example.manage_shops.request.RequestRegister;
import com.example.manage_shops.request.RequestUser;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.SecurityService;
import com.example.manage_shops.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceIpm implements UserService {
    private final UserRepo userRepo;
    private final AccountRepo accountRepo;
    private final RoleRepo roleRepo;
    private final RoleUserRepo roleUserRepo;
    private final SecurityService securityService;

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
    public void updateUser(User user) {
        userRepo.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<User> searchUserByKeyword(String keyword) {
        return userRepo.searchUserByKeyword(keyword);
    }
}
