package com.example.manage_shops.response;

import com.example.manage_shops.entity.Product;
import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseOrder {
    private long id;
    private LocalDate date;
    private Shop shop;
    private User seller;
    private Product product;
    private float quantity;
}
