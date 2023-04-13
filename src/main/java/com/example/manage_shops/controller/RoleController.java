package com.example.manage_shops.controller;

import com.example.manage_shops.entity.Role;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;
    private final Commons commons;

    public RoleController(RoleService roleService, Commons commons) {
        this.roleService = roleService;
        this.commons = commons;
    }

    @GetMapping("/getAll")
    public List<Role> getAllRole(String roleName) {
        return roleService.getAllRole(roleName);
    }
}
