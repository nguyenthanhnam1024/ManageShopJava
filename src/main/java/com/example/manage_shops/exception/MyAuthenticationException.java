package com.example.manage_shops.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.security.sasl.AuthenticationException;

@AllArgsConstructor
@Getter
public class MyAuthenticationException extends AuthenticationException {
    private final int status;

    private final String message;
}
