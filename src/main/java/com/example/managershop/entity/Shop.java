package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
public class Shop {
    @Id
    private Long id;
    private String ten;
    private String address;

    public Shop() {

    }
}
