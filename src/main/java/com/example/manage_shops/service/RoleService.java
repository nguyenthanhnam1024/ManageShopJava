package com.example.manage_shops.service;

import com.example.manage_shops.entity.Role;
import com.example.manage_shops.exception.MyValidateException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface RoleService {
    List<Role> getAllRole(HttpServletRequest httpServletRequest) throws MyValidateException;

    List<String> getListRoleName(HttpServletRequest httpServletRequest) throws MyValidateException;

    Role saveRole(HttpServletRequest httpServletRequest, Role role) throws MyValidateException;

    void deleteRole(HttpServletRequest httpServletRequest, int roleId) throws MyValidateException;
}
