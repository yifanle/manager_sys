package com.muzile.manage_sys.service.impl;

import com.muzile.manage_sys.dao.IRoleDao;
import com.muzile.manage_sys.domain.Permission;
import com.muzile.manage_sys.domain.Role;
import com.muzile.manage_sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleDao roleDao;

    @Override
    public List<Role> findAll() throws Exception{
        return roleDao.findAll();
    }

    @Override
    public Role getDetail(Integer id) throws Exception {
        return roleDao.getDetail(id);
    }

    @Override
    public void addRole(Role role) throws Exception {
        roleDao.addRole(role);
    }

    @Override
    public List<Role> findAllByRoleName(String roleName) throws Exception {
        return roleDao.findAllByRoleName(roleName);
    }

    @Override
    public List<Permission> loadPermissionsById(Integer id) throws Exception {
        return roleDao.loadPermissionsById(id);
    }

    @Override
    public void addPermissions(Role role) throws Exception {
        for(Integer pid:role.getPermissionIds()){
            roleDao.addPermissions(role.getId(),pid);
        }
    }
}
