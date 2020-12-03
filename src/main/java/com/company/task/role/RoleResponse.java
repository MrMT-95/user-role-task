package com.company.task.role;

import com.company.task.user.UserResponse;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;

public class RoleResponse {

    long id;
    String name;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    ArrayList<UserResponse> users;

    public RoleResponse(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public RoleResponse(long id, String name, ArrayList<UserResponse> users) {
        this.id = id;
        this.name = name;
        this.users = users;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UserResponse> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserResponse> users) {
        this.users = users;
    }


}
