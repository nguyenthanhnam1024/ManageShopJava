package com.example.manage_shops.controller;

import com.example.manage_shops.entity.Role;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping("/role")
@AllArgsConstructor
public class RoleController {
    private final RoleService roleService;
    private final Commons commons;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllRole(HttpServletRequest httpServletRequest) throws MyValidateException {
        return ResponseEntity.ok(roleService.getAllRole(httpServletRequest));
    }

    @GetMapping("/getRoleNames")
    public ResponseEntity<?> getListRoleName(HttpServletRequest httpServletRequest) throws MyValidateException {
        return ResponseEntity.ok(roleService.getListRoleName(httpServletRequest));
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveRole(HttpServletRequest httpServletRequest, @Valid @RequestBody Role role, BindingResult result) throws MyValidateException {
        if (result.hasErrors()) {
            return ResponseEntity.status(1000).body(commons.handleExceptionInBindingResult(result));
        }
        return ResponseEntity.ok(roleService.saveRole(httpServletRequest, role));
    }

    @DeleteMapping("/delete/{idRole}")
    public ResponseEntity<?> deleteRole(HttpServletRequest httpServletRequest, @PathVariable int idRole) throws MyValidateException {
        roleService.deleteRole(httpServletRequest, idRole);
        return ResponseEntity.ok().build();
    }
}
