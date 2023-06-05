package com.example.manage_shops.controller;

import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.request.RequestUpdateUser;
import com.example.manage_shops.request.RequestUser;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final Commons commons;

    @GetMapping("/getAll")
    public List<User> getAllUser(@RequestBody ResponseLogin responseLogin) throws MyValidateException {
        return userService.getAllUser(responseLogin);
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveUser(@Valid @RequestBody RequestUser requestUser, BindingResult result) throws MyValidateException {
        Map<String, String> errors = userService.errorCheckRequestRegisterFromADMINMap(requestUser);
        if (result.hasErrors() || !errors.isEmpty()) {
            Map<String, String> allError = commons.handleExceptionInBindingResult(result);
            allError.putAll(errors);
            return ResponseEntity.status(400).body(allError);
        }
        userService.saveUserFromADMIN(requestUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody RequestUpdateUser requestUpdateUser) throws MyValidateException {
        return ResponseEntity.ok(userService.updateUser(requestUpdateUser));
    }

    @PutMapping("/updateUserFromADMIN")
    public ResponseEntity<?> updateUserFromADMIN(@RequestBody RequestUser requestUser) throws MyValidateException {
        return ResponseEntity.ok(userService.updateUserFromADMIN(requestUser));
    }

    @GetMapping("/searchByKeyword")
    public ResponseEntity<?> getAllUser(@RequestParam("keyword") String keyword, @RequestParam("roleName") String roleName, @RequestBody ResponseLogin responseLogin) throws MyValidateException {
        return ResponseEntity.ok(userService.searchUserByKeyword(keyword, roleName, responseLogin));
    }
}
