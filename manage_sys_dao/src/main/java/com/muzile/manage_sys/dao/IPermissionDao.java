package com.muzile.manage_sys.dao;

import com.muzile.manage_sys.domain.Permission;
import com.muzile.manage_sys.domain.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IPermissionDao {

    @Select("select * from permission where id in (SELECT permission_id FROM role_permission WHERE role_id = #{id})")
    List<Permission> findByRoleId(Integer id) throws Exception;

    @Select("select * from permission")
    List<Permission> findAll() throws Exception;

    @Insert("insert into permission (permissionName,url) values (#{permissionName},#{url})")
    void addPermission(Permission permission) throws Exception;

    @Select("select * from permission where permissionName like '%${value}%'")
    List<Permission> findAllByPermissionName(String permissionName) throws Exception;

    @Delete("delete from permission where id = #{id}")
    void delPermission(Integer id);

    @Delete("<script>delete from permission where id in<foreach collection='array' item='id' open='(' separator=',' close=')'>#{id}</foreach></script>")
    void delPermissions(Integer[] ids);

    @Select("select * from role where id in (select role_id from role_permission where permission_id = (select id from permission where url = #{url}))")
    List<Role> getRoleByUrl(String url);
}
