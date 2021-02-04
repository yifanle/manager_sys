package com.muzile.manage_sys.service;

import com.muzile.manage_sys.domain.Permission;
import com.muzile.manage_sys.domain.Role;

import java.util.List;

public interface IPermissionService {
    List<Permission> findAll() throws Exception;

    void addPermission(Permission permission) throws Exception;

    List<Permission> findAllByPermissionName(String permissionName) throws Exception;

    void delPermission(Integer id) throws Exception;

    void delPermissions(Integer[] ids) throws Exception;

    List<Role> getRoleByUrl(String url) throws Exception;
}
