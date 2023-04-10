package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Order {
    @Id
    @NotNull
    private Long id;

    @NotNull
    private LocalDate date;

    @NotNull
    private Long idShop;

    @NotNull
    private Long idProduct;

    @NotNull
    @Min(1)
    private float quantity;

    public Order() {

    }
}
