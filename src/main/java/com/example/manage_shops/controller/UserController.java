package com.example.manage_shops.controller;

import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.impl.UserRepoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepoImpl userRepo;

    @GetMapping("/getUser")
    public ResponseEntity<?> getUser() throws MyValidateException {
        return ResponseEntity.ok(userRepo.getListUserByKeyword("ADMIN","a"));
    }
}
