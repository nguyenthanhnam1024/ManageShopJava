package com.example.manage_shops.service_ipm;

import com.example.manage_shops.dto.RoleDTO;
import com.example.manage_shops.entity.Role;
import com.example.manage_shops.exception.MyValidateException;
import com.example.manage_shops.repository.RoleRepo;
import com.example.manage_shops.service.Commons;
import com.example.manage_shops.service.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceIpm implements RoleService {
    private final RoleRepo roleRepo;
    private final Commons commons;

    public RoleServiceIpm(RoleRepo roleRepo, Commons commons) {
        this.roleRepo = roleRepo;
        this.commons = commons;
    }

    @Override
    public List<RoleDTO> getAllRole(int roleIdOfUser) throws MyValidateException {
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            return this.mapIntoListRoleDTO(roleRepo.findAll());
        }
        throw new MyValidateException(message);
    }

    @Override
    public RoleDTO getRoleById(int roleId, int roleIdOfUser) throws MyValidateException {
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            Optional<Role> roleExist = roleRepo.findById(roleId);
            if (roleExist.isPresent()) {
                return this.mapIntoRoleDTO(roleExist.get());
            }
            throw new MyValidateException("role name no have exist");
        }
        throw new MyValidateException(message);
    }

    @Override
    public RoleDTO saveRole(Role role, int roleIdOfUser) throws MyValidateException {
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            Optional<Role> roleExist = roleRepo.findByRoleName(role.getRoleName());
            if (!roleExist.isPresent()) {
                return this.mapIntoRoleDTO(roleRepo.save(role));
            }
            throw new MyValidateException("role name have been exist");
        }
        throw new MyValidateException(message);
    }

    @Override
    public RoleDTO updateRole(Role role, int roleIdOfUser) throws MyValidateException {
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            Optional<Role> roleExist = roleRepo.findById(role.getId());
            if (roleExist.isPresent()) {
                return this.mapIntoRoleDTO(roleRepo.save(role));
            }
            throw new MyValidateException("can't found this role in database to update");
        }
        throw new MyValidateException(message);
    }

    @Override
    public RoleDTO deleteRole(int roleId, int roleIdOfUser) throws MyValidateException {
        String message = commons.onlyValidateRoleForADMIN(roleIdOfUser);
        if (message == null) {
            Optional<Role> opRole = roleRepo.findById(roleId);
            if (!opRole.isPresent()) {
                throw new MyValidateException("can't found this role to delete");
            }
            roleRepo.deleteById(roleId);
            return this.mapIntoRoleDTO(opRole.get());
        }
        throw new MyValidateException(message);
    }

    @Override
    public RoleDTO mapIntoRoleDTO(Role role) {
        ModelMapper modelMapper = new ModelMapper();

        return modelMapper.map(role, RoleDTO.class);
    }

    @Override
    public List<RoleDTO> mapIntoListRoleDTO(List<Role> roleList) {
        return roleList.stream().map(this::mapIntoRoleDTO).collect(Collectors.toList());
    }
}
