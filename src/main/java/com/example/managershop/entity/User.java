package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
public class User {
    @Id
    @NotNull
    private Long id;

    @NotNull
    private Long idShop;

    @NotNull
    private Long idAccount;

    @NotNull
    @Size(min = 2, max = 50)
    private String name;

    @NotNull
    private int age;

    @NotNull
    @Email
    private String email;

    @NotNull
    @Size(min = 10, max = 15)
    private String phoneNumber;

    @NotNull
    @Size(min = 8, max = 60)
    private String address;

    public User() {

    }
}
