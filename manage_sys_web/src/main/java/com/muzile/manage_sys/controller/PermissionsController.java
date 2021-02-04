package com.muzile.manage_sys.controller;

import com.muzile.manage_sys.domain.Permission;
import com.muzile.manage_sys.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/permissions")
@ResponseBody
public class PermissionsController {

    @Autowired
    private IPermissionService permissionService;

    @RequestMapping("/findAllByPermissionName")
    public List<Permission> findAllByPermissionName(String permissionName){
        List<Permission> permissionList = null;
        try {
            permissionList = permissionService.findAllByPermissionName(permissionName);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return permissionList;
    }

    @RequestMapping("/delPermission")
    public void delPermission(Integer id){
        try {
            permissionService.delPermission(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/delPermissions")
    public void delPermissions(@RequestBody Integer[] ids){
        try {
            permissionService.delPermissions(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/addPermission")
    public void addPermission(@RequestBody Permission permission){
        try {
            permissionService.addPermission(permission);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findAll")
    public List<Permission> findAll(){
        List<Permission> permissionList = null;
        try {
            permissionList = permissionService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return permissionList;
    }
}
