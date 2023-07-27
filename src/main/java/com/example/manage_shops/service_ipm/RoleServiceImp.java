package com.example.manage_shops.service_ipm;

import com.example.manage_shops.entity.Role;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.jwt.ExtractDataFromJwt;
import com.example.manage_shops.repository.RoleRepo;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleServiceImp implements RoleService {
    private final Commons commons;
    private final RoleRepo roleRepo;
    private final ExtractDataFromJwt extractDataFromJwt;

    @Override
    public List<Role> getAllRole(HttpServletRequest httpServletRequest) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
        try {
            return roleRepo.findAll();
        } catch (Exception ex) {
            throw new MyValidateException("get list role failure");
        }
    }

    @Override
    public List<String> getListRoleName(HttpServletRequest httpServletRequest) throws MyValidateException {
        List<String> listRoleName = extractDataFromJwt.extractRoleNamesFromJwt(httpServletRequest);
        List<Role> listRole = roleRepo.findAll();
        for (String roleName : listRoleName) {
            for (Role role : listRole) {
                if (role.getRoleName().equals(roleName)) {
                    return listRole.stream().map(Role::getRoleName).collect(Collectors.toList());
                }
            }
        }
        throw new MyValidateException("restricted access");
    }


    @Override
    @Transactional
    public Role saveRole(HttpServletRequest httpServletRequest, Role role) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
        Optional<Role> roleOptional = roleRepo.findByRoleName(role.getRoleName());
        if (!roleOptional.isPresent()) {
            try {
                Role newRole = new Role();
                newRole.setRoleName(role.getRoleName());
                return roleRepo.save(newRole);
            } catch (Exception ex) {
                throw new MyValidateException("save role failure");
            }
        }
        throw new MyValidateException("Role name have been exist");
    }

    @Override
    public void deleteRole(HttpServletRequest httpServletRequest, int roleId) throws MyValidateException {
        commons.validateRoleForADMIN(httpServletRequest);
        try {
            Optional<Role> roleOptional = roleRepo.findById(roleId);
            if (roleOptional.isPresent()) {
                roleRepo.deleteById(roleId);
            }
        } catch (Exception ex) {
            throw new MyValidateException("delete failure");
        }
    }
}
