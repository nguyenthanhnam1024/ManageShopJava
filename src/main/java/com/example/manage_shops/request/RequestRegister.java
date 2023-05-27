package com.example.manage_shops.request;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestRegister {
    @NotBlank(message = "user name is null")
    @Size(min = 2, max = 50)
    private String userName;

    @NotBlank(message = "password is null")
    private String password;

    @NotNull(message = "shop must other null")
    private int idShop;

    @NotBlank(message = "full name must other null")
    @Size(min = 2, max = 50)
    private String name;

    @NotNull(message = "age must other null")
    private int age;

    @NotNull(message = "phone number must other null")
    @Size(min = 10, max = 15, message = "9 keyword < origin < 16 keyword")
    private String phoneNumber;


    @NotBlank(message = "email must other null")
    @Email(message = "email invalid")
    private String email;

    @NotBlank(message = "address must other null")
    @Size(min = 6, max = 60, message = "5 keyword < origin < 61 keyword")
    private String address;
}
