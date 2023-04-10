package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class User {
    @Id
    private Long id;
    private Long idShop;
    private Long idAccount;
    private String name;
    private int age;
    private String email;
    private String phoneNumber;
    private String address;

    public User() {

    }
}
