package com.company.task.role;

import com.company.task.user.User;
import com.company.task.user.UserRepository;
import com.company.task.user.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;


@Service
public class RoleServiceImpl implements RoleService {

    RoleRepository roleRepository;
    UserRepository userRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void addRole(String name) {

        //check for duplicate
        Optional<Role> roleOptional = roleRepository.findByName(name);
        roleOptional.ifPresent(role -> {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Given role already exists!");
        });

        //add role
        Role role = new Role(name);
        roleRepository.save(role);
        throw new ResponseStatusException(HttpStatus.OK, "Role added!");
    }

    @Override
    public void deleteRole(String name) {

        // check if role exist
        Optional<Role> roleOptional = roleRepository.findByName(name);
        roleOptional.orElseThrow(() -> new ResponseStatusException(HttpStatus.CONFLICT, "Given role does not exist!"));

        //delete role and connected users
        userRepository.deleteUsersByRoleName(name);
        roleRepository.deleteByName(name);
        throw new ResponseStatusException(HttpStatus.OK, "Role and users deleted!");
    }

    @Override
    public Iterable<RoleResponse> getRoles() {

        //create list of roles with connected users

        return roleRepository.findAll().stream()
                .map(Role::toRoleResponse)
                .peek(roleResponse -> roleResponse.setUsers(
                        userRepository.findAllByRoleName(roleResponse.getName()).stream()
                                .map(User::toUserResponseWithoutRole)
                                .collect(Collectors.toCollection(ArrayList::new))))
                .collect(Collectors.toList());
    }
}
