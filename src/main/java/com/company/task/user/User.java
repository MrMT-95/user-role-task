package com.company.task.user;

import com.company.task.role.Role;
import com.company.task.role.RoleResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    public User(String name, Role role) {
        this.name = name;
        this.role = role;
    }

    public User() {
        //empty constructor due to externalizable class
    }

    public UserResponse toUserResponseWithoutRole(){
        return new UserResponse(this.id, this.name);
    }


    public UserResponse toUserResponse(){
        RoleResponse roleResponse = new RoleResponse(this.role.getId(),this.role.getName());
        return new UserResponse(this.id, this.name,roleResponse);
    }


    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("name")
    private String name;
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    private Role role;
    public Role getRole() { return role; }
    public void setRole(Role role) {
        this.role = role;
    }






}
