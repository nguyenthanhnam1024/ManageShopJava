package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Role;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.RoleRepo;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService {
    private final Commons commons;
    private final RoleRepo roleRepo;

    @Override
    public List<Role> getAllRole(ResponseLogin responseLogin) throws MyValidateException {
        commons.validateRoleForADMIN(responseLogin.getRole());
        try {
            return roleRepo.findAll();
        } catch (Exception ex) {
            throw new MyValidateException("get list role failure");
        }
    }
}
