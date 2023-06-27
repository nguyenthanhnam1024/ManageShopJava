package com.example.manage_shops.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "shop must other null")
    private int idShop;

    @NotNull(message = "role must other null")
    private long idAccount;

    @NotNull(message = "role must other null")
    @Size(min = 2, max = 50)
    private String name;

    @NotNull(message = "role must other null")
    private int age;

    @NotNull(message = "role must other null")
    @Email(message = "email ")
    private String email;

    @NotNull(message = "role must other null")
    @Size(min = 10, max = 15, message = "9 keyword < origin < 16 keyword")
    private String phoneNumber;

    @NotNull(message = "role must other null")
    @Size(min = 6, max = 60, message = "5 keyword < origin < 61 keyword")
    private String address;
}
