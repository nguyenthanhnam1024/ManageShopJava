package com.example.manage_shops.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class RequestAccount {
    @NotNull(message = "user unknown")
    private String nameOfUser;

    @NotBlank(message = "account unknown")
    private String userNameOfAccount;

    @NotNull(message = "old password wrong")
    private String oldPassword;

    @NotBlank(message = "new password must other null and blank")
    @Size(min = 6, max = 50, message = "password from 6 to 50 keyword")
    private String newPassword;
}
