package com.example.manage_shops.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "name must other null and blank")
    @Size(min = 2, max = 50, message = "role must from 2 to 50 keyword")
    private String name;

    @NotNull(message = "address must other null")
    @Size(min = 6, max = 60, message = "address must from 6 to 60 keyword")
    private String address;

    @NotBlank(message = "hotline must other null and blank")
    @Size(min = 10, max = 10, message = "hotline invalid")
    private String hotline;
}
