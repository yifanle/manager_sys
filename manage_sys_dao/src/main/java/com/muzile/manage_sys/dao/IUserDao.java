package com.muzile.manage_sys.dao;

import com.muzile.manage_sys.domain.Role;
import com.muzile.manage_sys.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.muzile.manage_sys.dao.IRoleDao.findRolesById"))
    })
    UserInfo findByUsername(String username) throws Exception;

    @Select("select * from users")
    List<UserInfo> findAll() throws Exception;

    @Insert("insert into users (username,password,email,phoneNum,status) values(#{username},#{password},#{email},#{phoneNum},#{status})")
    void addUser(UserInfo userInfo) throws Exception;

    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,many = @Many(select = "com.muzile.manage_sys.dao.IRoleDao.findRolesById"))
    })
    UserInfo getDetail(Integer id) throws Exception;

    @Update("update users set status = 1 where id = #{id}")
    void authentic(Integer id) throws Exception;

    @Update("update users set status = 0 where id = #{id}")
    void shutdown(Integer id) throws Exception;

    @Select("select * from users where username like '%${value}%'")
    List<UserInfo> findAllByUsername(String searchName) throws Exception;

    @Select("SELECT * FROM role WHERE id NOT IN (SELECT roleId FROM role_users WHERE userId = #{id})")
    List<Role> loadRolesById(Integer id) throws Exception;

    @Insert("insert into role_users (userId,roleId) values (#{userId},#{roleId})")
    void addRoles(@Param("userId") Integer userId ,@Param("roleId") Integer roleId) throws Exception;
}
