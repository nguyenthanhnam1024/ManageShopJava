package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class DeliveryBill {
    @Id
    private Long id;
    private Long idProduct;
    private Long idShop;
    private Integer quantity;
    private LocalDate dateExport;

    public DeliveryBill() {

    }
}
