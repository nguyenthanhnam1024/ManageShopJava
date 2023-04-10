package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Account {
    @Id
    private Long id;
    private String userName;
    private String PassWord;

    public Account() {

    }
}
