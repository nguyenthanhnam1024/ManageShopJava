package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.my_enum.RoleEnum;
import com.example.manage_shops.repository.UserRepo;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceIpm implements UserService {
    private final UserRepo userRepo;

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
    public void saveUser(User user) {
        userRepo.save(user);
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
