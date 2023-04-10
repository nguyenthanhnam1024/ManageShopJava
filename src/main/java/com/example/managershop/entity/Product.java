package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Product {
    @Id
    private Long id;
    @Id
    private String name;
    private Long idShop;
    private float price;
    private String described;
    private LocalDate dateOfManufacture;
    private LocalDate expiry;
    private String origin;

    public Product() {

    }
}
