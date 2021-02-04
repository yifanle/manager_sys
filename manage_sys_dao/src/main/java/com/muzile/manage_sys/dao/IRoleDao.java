package com.muzile.manage_sys.dao;

import com.muzile.manage_sys.domain.Permission;
import com.muzile.manage_sys.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    @Select("select * from role where id in (select roleId from role_users where userId = #{id})")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select="com.muzile.manage_sys.dao.IPermissionDao.findByRoleId"))
    })
    List<Role> findRolesById(Integer id) throws Exception;

    @Select("select * from role")
    List<Role> findAll() throws Exception;

    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "roleName",column = "roleName"),
            @Result(property = "roleDesc",column = "roleDesc"),
            @Result(property = "permissions",column = "id",javaType = java.util.List.class,many = @Many(select="com.muzile.manage_sys.dao.IPermissionDao.findByRoleId"))
    })
    Role getDetail(Integer id) throws Exception;

    @Insert("insert into role (roleName,roleDesc) values (#{roleName},#{roleDesc})")
    void addRole(Role role) throws Exception;

    @Select("select * from role where roleName like '%${value}%'")
    List<Role> findAllByRoleName(String roleName) throws Exception;

    @Insert("insert into role_permission (role_id,permission_id) values (#{id},#{pid})")
    void addPermissions(@Param("id") Integer id, @Param("pid") Integer pid) throws Exception;

    @Select("SELECT * FROM permission WHERE id NOT IN (SELECT permission_id FROM role_permission WHERE role_id = #{id})")
    List<Permission> loadPermissionsById(Integer id) throws Exception;
}
