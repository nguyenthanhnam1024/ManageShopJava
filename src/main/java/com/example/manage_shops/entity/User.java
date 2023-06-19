package com.example.manage_shops.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@javax.persistence.Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "role must other null")
    private int idShop;

    @NotNull(message = "role must other null")
    @Column(name = "id_account")
    private Long idAccount;

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
    @Size(min = 8, max = 60, message = "7 keyword < origin < 61 keyword")
    private String address;

    public User() {

    }
}
