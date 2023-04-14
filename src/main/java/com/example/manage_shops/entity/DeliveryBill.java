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

    @NotNull(message = "no product")
    private Long idProduct;

    @NotNull(message = "shop must other null")
    private int idShop;

    @NotNull(message = "quantity must other null")
    @Min(value = 1, message = "quantity must be greater than 1")
    private Integer quantity;

    @NotNull(message = "date export must other null")
    private LocalDate dateExport;

    public DeliveryBill() {

    }
}
