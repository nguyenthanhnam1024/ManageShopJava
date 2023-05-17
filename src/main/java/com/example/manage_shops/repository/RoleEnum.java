package com.example.manage_shops.repository;

public enum RoleEnum {
    ADMIN(1, "ADMIN"),
    MANAGE(2, "MANAGE"),
    STAFF(3, "STAFF");

    private int idRole;
    private String roleName;

    RoleEnum(int idRole, String roleName) {
        this.idRole = idRole;
        this.roleName = roleName;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
