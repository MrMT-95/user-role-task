package com.company.task.user;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;


public interface UserRepository extends CrudRepository<User, Integer> {


    User findUserById (Integer id);

    Optional<User> findUserByName(String name);

    ArrayList<User> findAllByRoleName (String name);

    ArrayList<User> findAll ();

    @Transactional
    void deleteUserByName(String name);

    @Transactional
    void deleteUsersByRoleName(String role_name);

}
