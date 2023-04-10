package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.validation.constraints.NotNull;

@Entity
@Data
@AllArgsConstructor
@IdClass(IdRoleUser.class)
public class RoleUser {
    @Id
    @NotNull
    private Long idUser;

    @NotNull
    @Id
    private Long idRole;

    public RoleUser() {

    }
}
