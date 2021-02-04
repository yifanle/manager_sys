package com.muzile.manage_sys.controller;

import com.muzile.manage_sys.domain.Role;
import com.muzile.manage_sys.domain.UserInfo;
import com.muzile.manage_sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/users")
@ResponseBody
public class UsersController {

    @Autowired
    private IUserService userService;

    @RequestMapping("/addRoles")
    public void addRoles(@RequestBody UserInfo userInfo){
        try {
            userService.addRoles(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/loadRolesById")
    public List<Role> loadRolesById(Integer id){
        List<Role> roleList = null;
        try {
            roleList = userService.loadRolesById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roleList;
    }

    @RequestMapping("/findAllByUsername")
    public List<UserInfo> findAllByUsername(String username){
        List<UserInfo> userInfoList = null;
        try {
            userInfoList = userService.findAllByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfoList;
    }

    @RequestMapping("/shutdown")
    public void shutdown(Integer id){
        try {
            userService.shutdown(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/authentic")
    public void authentic(Integer id){
        try {
            userService.authentic(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/getDetail")
    public UserInfo getDetail(Integer id){
        UserInfo userInfo = null;
        try {
            userInfo = userService.getDetail(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfo;
    }

    @RequestMapping("/addUser")
    public void addUser(@RequestBody UserInfo userInfo){
        try {
            userService.addUser(userInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/findAll")
    public List<UserInfo> findAll(){
        List<UserInfo> userInfoList = null;
        try {
            userInfoList = userService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userInfoList;
    }

    @RequestMapping("/getCurrentUser")
    public String getCurrentUser(){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getUsername();
    }
}
