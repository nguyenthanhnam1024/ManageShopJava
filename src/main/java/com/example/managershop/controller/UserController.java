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

    @GetMapping("/getAll")
    public List<User> getAllUser() {
        return userService.getAllUser();
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
