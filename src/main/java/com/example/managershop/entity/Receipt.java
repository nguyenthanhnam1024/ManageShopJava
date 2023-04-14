package com.example.managershop.entity;

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

    @NotNull(message = "data added must other null")
    private LocalDate dateAdded;

    @NotNull(message = "shop must other null")
    private int idShop;

    @NotNull(message = "product must other null")
    private Long idProduct;

    @NotNull(message = "quantity must other null")
    @Min(value = 1, message = "quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "price must other null")
    private float price;

    public Receipt() {

    }
}
