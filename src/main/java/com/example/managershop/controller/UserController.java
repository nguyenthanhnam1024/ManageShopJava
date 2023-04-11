package com.example.managershop.controller;

import com.example.managershop.entity.User;
import com.example.managershop.service.UserService;
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

    @GetMapping("/getAllUser")
    public List<User> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping("/saveUser")
    public void saveUser(@Valid @RequestBody User user) {
        userService.saveUser(user);
    }

}
