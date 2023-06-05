package com.example.manage_shops.my_enum;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleEnum {
    ADMIN(1, "ADMIN"),
    MANAGE(2, "MANAGE"),
    STAFF(3, "STAFF");
    private final int roleId;
    private final String roleName;
}
