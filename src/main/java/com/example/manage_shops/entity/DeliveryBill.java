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
public class DeliveryBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Long idProduct;

    @NotNull
    private Long idShop;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private LocalDate dateExport;

    public DeliveryBill() {

    }
}
