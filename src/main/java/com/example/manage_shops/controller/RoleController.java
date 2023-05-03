package com.example.manage_shops.controller;

import com.example.manage_shops.entity.Role;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.RoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    private final Commons commons;

    public RoleController(RoleService roleService, Commons commons) {
        this.roleService = roleService;
        this.commons = commons;
    }

    @CrossOrigin(origins = "http://localhost:8080", maxAge = 36000000L)
    @GetMapping("/getAll/{roleId}")
    public ResponseEntity<?> getAllRole(@PathVariable int roleId) throws MyValidateException {
        return ResponseEntity.ok(roleService.getAllRole(roleId));
    }

    @CrossOrigin(origins = "http://localhost:8080", maxAge = 36000000L)
    @GetMapping("/getAll/{roleId}/{roleIdUser}")
    public ResponseEntity<?> getAllRole(@PathVariable int roleId, @PathVariable int roleIdUser) throws MyValidateException {
        return ResponseEntity.ok(roleService.getRoleById(roleId, roleIdUser));
    }

    @CrossOrigin(origins = "http://localhost:8080", maxAge = 36000000L)
    @PostMapping("/save/{roleIdUser}")
    public ResponseEntity<?> saveRole(@RequestBody Role role, @PathVariable int roleIdUser, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(roleService.saveRole(role, roleIdUser));
    }

    @CrossOrigin(origins = "http://localhost:8080", maxAge = 36000000L)
    @PutMapping("/update/{roleIdUser}")
    public ResponseEntity<?> updateRole(@RequestBody Role role, @PathVariable int roleIdUser, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(roleService.updateRole(role, roleIdUser));
    }

    @CrossOrigin(origins = "http://localhost:8080", maxAge = 36000000L)
    @DeleteMapping("/delete/{roleId}/{roleIdUser}")
    public ResponseEntity<?> deleteRole(@PathVariable int roleId, @PathVariable int roleIdUser) throws MyValidateException{
        return ResponseEntity.ok(roleService.deleteRole(roleId, roleIdUser));
    }
}
