package com.example.manage_shops.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "name product is null")
    private String name;

    @NotNull(message = "shop must other null")
    private int idShop;

    @NotNull(message = "price must other null")
    private float price;

    @Size(max = 1000, message = "described must be less 1000 keyword")
    private String described;

    @NotNull(message = "dataOfManufacture must other null")
    @PastOrPresent(message = "date must be present or past")
    private LocalDate dateOfManufacture;

    @NotNull(message = "expiry must other null")
    private LocalDate expiry;

    @NotNull(message = "origin must other null")
    @Size(min = 2, max = 50, message = "2 keyword < origin < 51 keyword")
    private String origin;

    public Product() {

    }
}
