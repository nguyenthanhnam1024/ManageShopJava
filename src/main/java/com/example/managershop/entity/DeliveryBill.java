package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
public class DeliveryBill {
    @Id
    @NotNull
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
