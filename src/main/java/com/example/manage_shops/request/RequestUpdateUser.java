package com.example.manage_shops.request;

import com.example.manage_shops.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class RequestUpdateUser {
    private Shop shop;

    private String role;

    @NotBlank(message = "role must other null")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "role must other null")
    @Size(min = 2, max = 50)
    private String oldName;

    private String userNameOfAccount;

    @NotNull(message = "age must other null")
    private int age;

    @NotBlank(message = "email must other null")
    @Email(message = "email invalid")
    private String email;

    @NotBlank(message = "role must other null")
    @Size(min = 10, max = 15, message = "9 keyword < origin < 16 keyword")
    private String phoneNumber;

    @NotBlank(message = "address must other null")
    @Size(min = 6, max = 60, message = "5 keyword < origin < 61 keyword")
    private String address;
}
