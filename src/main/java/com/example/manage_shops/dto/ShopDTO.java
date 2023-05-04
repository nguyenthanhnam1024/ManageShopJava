package com.example.manage_shops.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class ShopDTO {
    private int id;

    private String name;

    private String address;

    private String hotline;
}
