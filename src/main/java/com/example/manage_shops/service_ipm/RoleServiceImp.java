package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Role;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.RoleRepo;
import com.example.manage_shops.request.RequestRole;
import com.example.manage_shops.response.ResponseLogin;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    @Override
    @Transactional
    public void saveRole(RequestRole requestRole) throws MyValidateException {
        commons.validateRoleForADMIN(requestRole.getResponseLogin().getRole());
        Optional<Role> roleOptional = roleRepo.findByRoleName(requestRole.getRoleName());
        if (!roleOptional.isPresent()) {
            try {
                Role newRole = new Role();
                newRole.setRoleName(requestRole.getRoleName());
                roleRepo.save(newRole);
            } catch (Exception ex) {
                throw new MyValidateException("save role failure");
            }
        }
        throw new MyValidateException("Role name have been exist");
    }
}
