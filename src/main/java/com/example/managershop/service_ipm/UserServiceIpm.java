package com.example.managershop.service_ipm;

import com.example.managershop.entity.User;
import com.example.managershop.repository.UserRepo;
import com.example.managershop.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceIpm implements UserService {
    private final UserRepo userRepo;

    public UserServiceIpm(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<User> getAllUser() {
        return userRepo.findAll();
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
