package com.example.manage_shops.request;

import com.example.manage_shops.response.ResponseLogin;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class RequestRole {
    private String roleName;

    private ResponseLogin responseLogin;
}
