package com.muzile.manage_sys.dao;

import com.muzile.manage_sys.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface ISysLogDao {

    @Insert("insert into logs (visitTime,username,ip,url,executionTime,method) values(#{visitTime},#{username},#{ip},#{url},#{executionTime},#{method})")
    void save(SysLog sysLog) throws Exception;

    @Select("select * from logs")
    List<SysLog> findAll() throws Exception;

    @Insert("insert into sysSet (email) values (#{email})")
    void saveEmail(String email) throws Exception;

    @Select("select email from sysSet where id = 1")
    String getEmail() throws Exception;

    @Update("update sysSet set email = #{email} where id = 1")
    void updateEmail(String email) throws Exception;
}
