package com.example.manage_shops.repository;

import com.example.manage_shops.entity.IdRoleUser;
import com.example.manage_shops.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserRepo extends JpaRepository<RoleUser, IdRoleUser> {
}
