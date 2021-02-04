package com.muzile.manage_sys.service.impl;

import com.muzile.manage_sys.dao.IUserDao;
import com.muzile.manage_sys.domain.Role;
import com.muzile.manage_sys.domain.UserInfo;
import com.muzile.manage_sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        User user = null;
        try {
            userInfo = userDao.findByUsername(username);
            user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==1?true:false,true,true,true,getAuthority(userInfo.getRoles()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private List<SimpleGrantedAuthority> getAuthority(List<Role> roles) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role :roles){
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        return userDao.findAll();
    }

    @Override
    public void addUser(UserInfo userInfo) throws Exception {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.addUser(userInfo);
    }

    @Override
    public UserInfo getDetail(Integer id) throws Exception {
        return userDao.getDetail(id);
    }

    @Override
    public void shutdown(Integer id) throws Exception {
        userDao.shutdown(id);
    }

    @Override
    public void authentic(Integer id) throws Exception {
        userDao.authentic(id);
    }

    @Override
    public List<UserInfo> findAllByUsername(String searchName) throws Exception {
        return userDao.findAllByUsername(searchName);
    }

    @Override
    public List<Role> loadRolesById(Integer id) throws Exception {
        return userDao.loadRolesById(id);
    }

    @Override
    public void addRoles(UserInfo userInfo) throws Exception {
        for(Integer roleId : userInfo.getRoleIds()){
            userDao.addRoles(userInfo.getId(),roleId);
        }
    }
}
