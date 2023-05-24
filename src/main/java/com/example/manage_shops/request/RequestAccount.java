package com.example.manage_shops.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor
@Data
public class RequestAccount {
    @NotBlank(message = "user unknown")
    private String nameOfUser;

    @NotBlank(message = "account unknown")
    private String userNameOfAccount;

    @NotBlank(message = "account unknown")
    private String oldPassword;

    @Size(min = 6, max = 50)
    @NotBlank(message = "new password must not null, empty")
    private String newPassword;
}
