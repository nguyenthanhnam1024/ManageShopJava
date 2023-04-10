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
public class Role {
    @Id
    @NotNull
    private Long id;

    @NotNull
    @Size(min = 1, max = 15)
    private String role;

    public Role() {

    }
}
