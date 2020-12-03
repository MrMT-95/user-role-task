package com.company.task.role;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, String> {

    Optional<Role> findByName(String name);

    ArrayList<Role> findAll();

    @Transactional
    void deleteByName(String name);


}
