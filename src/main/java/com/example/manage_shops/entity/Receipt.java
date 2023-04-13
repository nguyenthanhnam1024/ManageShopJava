package com.example.manage_shops.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDate dateAdded;

    @NotNull
    private Long idShop;

    @NotNull
    private Long idProduct;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    @Min(0)
    private float price;

    public Receipt() {

    }
}
