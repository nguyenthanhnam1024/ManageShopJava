package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class Receipt {
    @Id
    private Long id;
    private LocalDate dateAdded;
    private Long idShop;
    private Long idProduct;
    private Integer quantity;
    private float price;

    public Receipt() {

    }
}
