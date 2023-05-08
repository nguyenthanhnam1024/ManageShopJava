package com.example.manage_shops.my_enum;

public enum RoleEnum {
    ADMIN(1, "ADMIN"),
    MANAGE(2, "MANAGE"),
    STAFF(3, "STAFF");

    private final int idRole;
    private final String roleName;

    RoleEnum(int idRole, String roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }

    public int getIdRole() {
        return idRole;
    }

    public String getRoleName() {
        return roleName;
    }
}
