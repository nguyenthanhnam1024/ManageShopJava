package com.example.manage_shops.controller;

import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.request.RequestRole;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;

    @GetMapping("getAll")
    public ResponseEntity<?> getAllRole(@RequestBody ResponseLogin responseLogin) throws MyValidateException {
        return ResponseEntity.ok(roleService.getAllRole(responseLogin));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRole(@RequestBody RequestRole requestRole) throws MyValidateException {
        roleService.saveRole(requestRole);
        return ResponseEntity.ok().build();
    }
}
