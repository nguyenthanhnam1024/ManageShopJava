package com.example.managershop.service;

import com.example.managershop.entity.User;

import java.util.List;

public interface UserService {
     List<User> getAllUser();

     void saveUser(User user);

     void updateUser(User user);

     void deleteUser(Long id);
}
