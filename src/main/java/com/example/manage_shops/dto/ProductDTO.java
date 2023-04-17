package com.example.manage_shops.dto;

import com.example.manage_shops.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ProductDTO {
    private Long id;

    private String name;

    private Shop shop;

    private float price;

    private String described;

    private LocalDate dateOfManufacture;

    private LocalDate expiry;

    private String origin;
}
