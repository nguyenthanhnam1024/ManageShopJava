package com.example.manage_shops.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@IdClass(IdRoleUser.class)
public class RoleUser {
    @Id
    private long idUser;

    @Id
    private int idRole;
}
