package com.example.managershop.repository;

import com.example.managershop.entity.IdRoleUser;
import com.example.managershop.entity.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleUserRepo extends JpaRepository<RoleUser, IdRoleUser> {
}
