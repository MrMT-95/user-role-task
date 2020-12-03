package com.company.task.user;

import com.company.task.role.RoleResponse;

import com.fasterxml.jackson.annotation.JsonInclude;

public class UserResponse {


    int id;
    String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    RoleResponse role;

    public UserResponse(int id, String name, RoleResponse role) {
        this.id = id;
        this.name = name;
        this.role = role;
    }


    public UserResponse(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoleResponse getRole() {
        return role;
    }

    public void setRole(RoleResponse role) {
        this.role = role;
    }


}
