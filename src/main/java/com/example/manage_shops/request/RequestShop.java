package com.example.manage_shops.request;

import com.example.manage_shops.entity.Shop;
import com.example.manage_shops.response.ResponseLogin;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;

@AllArgsConstructor
@Getter
public class RequestShop {
    @Valid
    private final Shop shop;

    private final ResponseLogin responseLogin;
}
