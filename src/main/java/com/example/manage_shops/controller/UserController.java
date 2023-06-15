package com.example.manage_shops.controller;

import com.example.manage_shops.entity.User;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.request.RequestRegister;
import com.example.manage_shops.request.RequestUpdateUser;
import com.example.manage_shops.request.RequestUser;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.SecurityService;
import com.example.manage_shops.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private final UserService userService;
    private final Commons commons;
    private final SecurityService securityService;

    @GetMapping("/getAll/{idShop}")
    public List<User> getAllUser(HttpServletRequest httpServletRequest, @PathVariable int idShop) throws MyValidateException {
        return userService.getAllUser(httpServletRequest, idShop);
    }

    @GetMapping("/getById/{idUser}")
    public ResponseEntity<?> getById(HttpServletRequest httpServletRequest, @PathVariable long idUser) throws MyValidateException {
        return ResponseEntity.ok(userService.getById(httpServletRequest, idUser));
    }
    @PostMapping("/saveFromADMIN")
    public ResponseEntity<?> saveUserFromAdmin(HttpServletRequest httpServletRequest, @Valid @RequestBody RequestUser requestUser, BindingResult result) throws MyValidateException {
        ModelMapper modelMapper = new ModelMapper();
        Map<String, String> errors = securityService.errorCheckRequestRegisterMap(modelMapper.map(requestUser, RequestRegister.class));
        if (requestUser.getRoleName() != null) {
            if (requestUser.getRoleName().equals("ADMIN") && requestUser.getIdShop() != 0 || requestUser.getIdShop() == 0 && !requestUser.getRoleName().equals("ADMIN")) {
                errors.put("idShop", "reselect shop");
            }
        }
        if (result.hasErrors() || !errors.isEmpty()) {
            Map<String, String> allError = commons.handleExceptionInBindingResult(result);
            allError.putAll(errors);
            return ResponseEntity.status(1000).body(allError);
        }
        userService.saveUserFromADMIN(httpServletRequest, requestUser);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUser(@Valid @RequestBody RequestUpdateUser requestUpdateUser, BindingResult result) throws MyValidateException {
        Map<String, String> errors = new HashMap<>();
        if (requestUpdateUser.getAge() <= 0) {
            errors.put("age", "age must be > 0");
        }
        if (result.hasErrors() || !errors.isEmpty()) {
            Map<String, String> allError = commons.handleExceptionInBindingResult(result);
            allError.putAll(errors);
            return ResponseEntity.status(1000).body(allError);
        }

        return ResponseEntity.ok(userService.updateUser(requestUpdateUser));
    }

    @PutMapping("/updateFromADMIN")
    public ResponseEntity<?> updateUserFromADMIN(HttpServletRequest httpServletRequest, @RequestBody RequestUser requestUser) throws MyValidateException {
        if (requestUser.getRoleName() != null) {
            Map<String, String> error = new HashMap<>();
             if (requestUser.getRoleName().equals("ADMIN") && requestUser.getIdShop() != 0 || requestUser.getIdShop() == 0 && !requestUser.getRoleName().equals("ADMIN")) {
                error.put("idShop", "reselect shop");
             }
             if (!error.isEmpty()) {
                 return ResponseEntity.status(1000).body(error);
             }
        }
        return ResponseEntity.ok(userService.updateUserFromADMIN(httpServletRequest, requestUser));
    }

    @GetMapping("/searchByKeyword")
    public ResponseEntity<?> getAllUser(HttpServletRequest httpServletRequest, @RequestParam("keyword") String keyword, @RequestParam("roleName") String roleName) throws MyValidateException {
        return ResponseEntity.ok(userService.searchUserByKeyword(httpServletRequest, keyword, roleName));
    }

    @DeleteMapping("/delete/{idUser}")
    public ResponseEntity<?> deleteUser(HttpServletRequest httpServletRequest, @PathVariable Long idUser) throws MyValidateException {
        userService.deleteUser(httpServletRequest, idUser);
        return ResponseEntity.ok().build();
    }
}
