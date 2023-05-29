package com.example.manage_shops.controller;

import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/getAll")
    public List<User> getAllUser(@RequestBody ResponseLogin responseLogin) throws MyValidateException {
        return userService.getAllUser(responseLogin);
    }

    @PostMapping("/save")
    public void saveUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
    }

    @PutMapping("/update")
    public void updateUser(@Valid @RequestBody User user) {
        userService.updateUser(user);
    }

    @DeleteMapping("delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/searchByKeyword")
    public List<User> getAllUser(@RequestParam("keyword") String keyword) {
        return userService.searchUserByKeyword(keyword);
    }
}
