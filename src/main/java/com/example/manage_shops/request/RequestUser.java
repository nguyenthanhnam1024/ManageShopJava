package com.example.manage_shops.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.*;

@AllArgsConstructor
@Data
@RequiredArgsConstructor
public class RequestUser {
    @NotBlank(message = "user name is null and blank")
    @Size(min = 2, max = 50)
    private String userName;

    @NotNull(message = "password must other null")
    @Size(min = 6, max = 50, message = "password must from 6 to 50 keyword")
    private String password;

    @NotNull(message = "shop must other null")
    @Min(value = 1, message = "shop invalid")
    private int idShop;

    @NotBlank(message = "role name must other null and blank")
    private String roleName;

    @NotNull(message = "full name must other null")
    @Size(min = 2, max = 50, message = "name from 2 to 50 keyword")
    private String name;

    @NotNull(message = "age must other null")
    @Min(value = 6, message = "age appropriate from 6 to 120")
    @Max(value = 120, message = "age appropriate from 6 to 120")
    private int age;

    @NotBlank(message = "phone number must other null and blank")
    @Size(min = 10, max = 10, message = "phone number invalid")
    private String phoneNumber;


    @NotBlank(message = "email must other null and blank")
    @Email(message = "email invalid")
    private String email;

    @NotNull(message = "address must other null")
    @Size(min = 6, max = 60, message = "address must from 6 to 60 keyword")
    private String address;
}
