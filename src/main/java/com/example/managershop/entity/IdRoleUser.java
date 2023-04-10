package com.example.managershop.entity;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class IdRoleUser implements Serializable {
    @NotNull
    Long idUser;

    @NotNull
    Long IDRole;
}
