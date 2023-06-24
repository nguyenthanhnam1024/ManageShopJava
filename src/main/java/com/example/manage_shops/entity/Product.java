package com.example.manage_shops.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "name product must other null")
    private String name;

    @NotNull(message = "shop must other null")
    private int idShop;

    @NotNull(message = "price must other null")
    @Min(value = 1, message = "price must be large or equal 1")
    private float price;

    @Size(max = 1000, message = "described must be less 1000 keyword")
    private String described;

    @NotNull(message = "dataOfManufacture must other null")
    @PastOrPresent(message = "date must be present or past")
    private LocalDate dateOfManufacture;

    @NotNull(message = "expiry must other null")
    @FutureOrPresent(message = "date must be present or future")
    private LocalDate expiry;

    @NotNull(message = "origin must other null")
    @Size(min = 2, max = 50, message = "2 keyword < origin < 51 keyword")
    private String origin;
}
