package com.muzile.manage_sys.controller;

import com.muzile.manage_sys.domain.Permission;
import com.muzile.manage_sys.domain.Role;
import com.muzile.manage_sys.domain.UserInfo;
import com.muzile.manage_sys.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/roles")
@ResponseBody
public class RolesController {

    @Autowired
    private IRoleService roleService;

    @RequestMapping("/addPermissions")
    public void addPermissions(@RequestBody Role role){
        try {
            roleService.addPermissions(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/loadPermissionsById")
    public List<Permission> loadPermissionsById(Integer id){
        List<Permission> permissionList = null;
        try {
            permissionList = roleService.loadPermissionsById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return permissionList;
    }

    @RequestMapping("/findAllByRoleName")
    public List<Role> findAllByUsername(String roleName){
        List<Role> roleList = null;
        try {
            roleList = roleService.findAllByRoleName(roleName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }

    @RequestMapping("/getDetail")
    public Role getDetail(Integer id){
        Role role = null;
        try {
            role = roleService.getDetail(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return role;
    }

    @RequestMapping("/addRole")
    public void addRole(@RequestBody Role role){
        try {
            roleService.addRole(role);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findAll")
    public List<Role> findAll(){
        List<Role> roleList = null;
        try {
            roleList = roleService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }
}
