package com.example.manage_shops.service;

import com.example.manage_shops.entity.User;

import java.util.List;

public interface UserService {
     List<User> getAllUser();

     void saveUser(User user);

     void updateUser(User user);

     void deleteUser(Long id);

     List<User> searchUserByKeyword(String keyword);
}
