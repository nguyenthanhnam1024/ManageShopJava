package com.example.manage_shops.service;

import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.response.ResponseLogin;

import java.util.List;

public interface UserService {
     List<User> getAllUser(ResponseLogin responseLogin) throws MyValidateException;

     void saveUser(User user);

     void updateUser(User user);

     void deleteUser(Long id);

     List<User> searchUserByKeyword(String keyword);
}
