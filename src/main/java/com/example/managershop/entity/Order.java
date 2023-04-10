package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Order {
    @Id
    private Long id;
    private LocalDate date;
    private Long idShop;
    private Long idProduct;
    private float quantity;

    public Order() {

    }
}
