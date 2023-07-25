package com.example.manage_shops.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "unknown shop")
    private int idShop;

    @NotNull(message = "unknown account")
    private long idAccount;

    @NotNull(message = "role must other null")
    @Size(min = 2, max = 50, message = "name must from 6 to 60 keyword")
    private String name;

    @NotNull(message = "age must other null")
    @Min(value = 6, message = "age appropriate from 6 to 120")
    @Max(value = 120, message = "age appropriate from 6 to 120")
    private int age;

    @NotBlank(message = "email must other null and blank")
    @Email(message = "email invalid")
    private String email;

    @NotBlank(message = "phone number must other null and blank")
    @Size(min = 10, max = 10, message = "phone number invalid")
    private String phoneNumber;

    @NotNull(message = "address must other null")
    @Size(min = 6, max = 60, message = "address must from 6 to 60 keyword")
    private String address;
}
