package com.company.task.user;

public interface UserService {

    void addUser(UserRequest userRequest);
    void deleteUser(String name);
    Iterable<UserResponse> getUsers();

}
