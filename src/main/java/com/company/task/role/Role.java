package com.company.task.role;

import com.company.task.user.User;
import com.company.task.user.UserResponse;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Role() {
    }

    @JsonProperty("name")
    private String name;


    public Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToMany
    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> user) {
        this.users = user;
    }

    public RoleResponse toRoleResponse(){
        ArrayList<UserResponse> userResponseArrayList = this.users.stream()
                .map(User::toUserResponse)
                .collect(Collectors.toCollection(ArrayList::new));

        return new RoleResponse(this.getId(), this.getName(),userResponseArrayList);
    }
}
