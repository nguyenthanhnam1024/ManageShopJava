package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Role;
import com.example.manage_shops.repository.RoleEnum;
import com.example.manage_shops.repository.RoleRepo;
import com.example.manage_shops.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@SuppressWarnings("unused")
public class RoleServiceIpm implements RoleService {
    private final RoleRepo roleRepo;

    public RoleServiceIpm(RoleRepo roleRepo) {
        this.roleRepo = roleRepo;
    }

    @Override
    public List<Role> getAllRole(String roleName) {
        try {
            RoleEnum role = RoleEnum.valueOf(roleName);
            return roleRepo.findAll();
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("unknown role of user", ex);
        }
    }
}
