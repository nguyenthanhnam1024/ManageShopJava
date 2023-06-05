package com.example.manage_shops.response;

import com.example.manage_shops.entity.Shop;
import lombok.Data;

@Data
public class ResponseLogin {
    private Shop shop;

    private String role;

    private String name;
    
    private String userNameOfAccount;

    private int age;

    private String email;

    private String phoneNumber;

    private String address;
}