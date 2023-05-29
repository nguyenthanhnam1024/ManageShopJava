package com.example.manage_shops.controller;

import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAllRole(@RequestBody ResponseLogin responseLogin) throws MyValidateException {
        return ResponseEntity.ok(roleService.getAllRole(responseLogin));
    }
}
