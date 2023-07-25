package com.example.manage_shops.request;

import com.example.manage_shops.entity.Shop;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@AllArgsConstructor
@Data
public class RequestUpdateUser {
    private Shop shop;

    private String role;

    @NotBlank(message = "new name must other null and blank")
    @Size(min = 2, max = 50)
    private String name;

    @NotBlank(message = "old name must other null and blank")
    @Size(min = 2, max = 50)
    private String oldName;

    private String userNameOfAccount;

    @NotNull(message = "age must other null")
    @Min(value = 6, message = "age appropriate from 6 to 120")
    @Max(value = 120, message = "age appropriate from 6 to 120")
    private int age;

    @NotBlank(message = "email must other null and blank")
    @Email(message = "email invalid")
    private String email;

    @NotBlank(message = "phone number must other null and blank")
    @Size(min = 10, max = 10, message = "phone number invalid")
    private String phoneNumber;

    @NotNull(message = "address must other null")
    @Size(min = 6, max = 60, message = "address must from 6 to 60 keyword")
    private String address;
}
