package com.example.manage_shops.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Table(name = "order_product")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "no date create order")
    @PastOrPresent(message = "date must be present or past")
    private LocalDate date;

    @Min(value = 1, message = "shop invalid")
    private int idShop;

    @Min(value = 0, message = "the seller is not a store employee")
    private long idSeller;

    @Min(value = 1, message = "product invalid")
    private long idProduct;

    @NotNull(message = "quantity must other null")
    @Min(value = 1, message = "quantity must be greater than 1")
    private float quantity;
}
