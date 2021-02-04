package com.muzile.manage_sys.service;

import com.muzile.manage_sys.domain.Permission;
import com.muzile.manage_sys.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll() throws Exception;

    Role getDetail(Integer id) throws Exception;

    void addRole(Role role) throws Exception;

    List<Role> findAllByRoleName(String roleName) throws Exception;

    List<Permission> loadPermissionsById(Integer id) throws Exception;

    void addPermissions(Role role) throws Exception;
}
