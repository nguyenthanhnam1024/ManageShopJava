package com.example.managershop.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@AllArgsConstructor
public class Account {
    @Id
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 2, max = 50)
    private String userName;

    @NotNull
    @Size(min = 6, max = 50)
    private String PassWord;

    public Account() {

    }
}
