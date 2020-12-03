package com.company.task.user;

import com.company.task.role.Role;
import com.company.task.role.RoleRepository;
import com.company.task.role.RoleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    RoleRepository roleRepository;
    UserRepository userRepository;


    @Autowired
    public UserServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addUser(UserRequest userRequest) {
        String name = userRequest.getName();

        // check if user role exists
        Optional<Role> roleOptional = roleRepository.findByName(userRequest.getRole());
        Role role = roleOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Given role does not exist!"));

        // check for duplicate
        Optional<User> userOptional = userRepository.findUserByName(name);
        userOptional.ifPresent(user -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Given user already exists");
        });

        // create user
        User user = new User(name, role);
        userRepository.save(user);
        throw new ResponseStatusException(HttpStatus.OK, "User added!");

    }

    @Override
    public void deleteUser(String name) {

        // check if user exists
        Optional<User> optionalUser = userRepository.findUserByName(name);
        optionalUser.orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Given user does not exist!"));

        //delete user
        userRepository.deleteUserByName(name);
        throw new ResponseStatusException(HttpStatus.OK, "User deleted!");
    }

    @Override
    public Iterable<UserResponse> getUsers() {

        // create list of users with connected roles
        return userRepository.findAll().stream()
                .map(User::toUserResponse)
                .collect(Collectors.toList());
    }
}
