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
public class Product {
    @Id
    @NotNull
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Long idShop;

    @NotNull
    @Min(0)
    private float price;

    @NotNull
    @Size(min = 0, max = 1000)
    private String described;

    @NotNull
    private LocalDate dateOfManufacture;

    @NotNull
    private LocalDate expiry;

    @NotNull
    @Size(min = 2, max = 50)
    private String origin;

    public Product() {

    }
}
