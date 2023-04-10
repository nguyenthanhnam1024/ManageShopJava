package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
public class Shop {
    @Id
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String ten;

    @NotNull
    @Size(min = 8, max = 60)
    private String address;

    public Shop() {

    }
}
