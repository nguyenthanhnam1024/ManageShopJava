package com.example.manage_shops.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "role must other null")
    @Size(min = 2, max = 50, message = "1 keyword < origin < 51 keyword")
    private String name;

    @NotNull
    @Size(min = 6, max = 60, message = "5 keyword < origin < 61 keyword")
    private String address;

    @NotNull
    @Size(min = 10, max = 15, message = "7 keyword < hotline < 61 keyword")
    private String hotline;

    public Shop() {

    }
}
