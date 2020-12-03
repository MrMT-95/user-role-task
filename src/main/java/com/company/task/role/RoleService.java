package com.company.task.role;

public interface RoleService {

    void addRole(String name);
    void deleteRole(String name);
    Iterable<RoleResponse> getRoles();

}
