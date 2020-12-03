package com.company.task.controllers;

import com.company.task.role.RoleResponse;
import com.company.task.role.RoleService;
import com.company.task.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class TaskController {

    UserService userService;
    RoleService roleService;

    @Autowired
    public TaskController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }


    //User
    @PostMapping(value = "/users", consumes = "application/json")
    public void addUser(@RequestBody UserRequest userRequest) {
        userService.addUser(userRequest);
    }

    @DeleteMapping("/users")
    public void deleteUser(@RequestParam String name) {
        userService.deleteUser(name);
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<UserResponse>> getUsers() {
        Iterable<UserResponse> userList = userService.getUsers();
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    //Role
    @PostMapping("/roles")
    public void addRole(@RequestParam String name) {
        roleService.addRole(name);
    }

    @DeleteMapping("/roles")
    public void deleteRole(@RequestParam String name) {
        roleService.deleteRole(name);
    }

    @GetMapping("/roles")
    public ResponseEntity<Iterable<RoleResponse>> getRoles() {
        return new ResponseEntity<>(roleService.getRoles(),HttpStatus.OK);
    }

}
