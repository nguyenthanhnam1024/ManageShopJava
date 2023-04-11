package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@AllArgsConstructor
@IdClass(IdRoleUser.class)
public class RoleUser {
    @Id
    private Long idUser;

    @Id
    private Long idRole;

    public RoleUser() {

    }
}
