package com.example.manage_shops.service;

import com.example.manage_shops.dto.RoleDTO;
import com.example.manage_shops.entity.Role;
import com.example.manage_shops.exception.MyValidateException;

import java.util.List;

public interface RoleService {
    String validateRole(int roleId);

    List<RoleDTO> getAllRole(int roleIdOfUser) throws MyValidateException;

    RoleDTO getRoleById(int roleId, int roleIdOfUser) throws MyValidateException;

    RoleDTO saveRole(Role role, int roleIdOfUser) throws MyValidateException;

    RoleDTO updateRole(Role role, int roleIdOfUser) throws MyValidateException;

    RoleDTO deleteRole(int roleId, int roleIdOfUser) throws MyValidateException;

    RoleDTO mapIntoRoleDTO(Role role);

    List<RoleDTO> mapIntoListRoleDTO(List<Role> roleList);
}
