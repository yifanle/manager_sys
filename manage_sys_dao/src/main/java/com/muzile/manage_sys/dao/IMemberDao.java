package com.muzile.manage_sys.dao;

import com.muzile.manage_sys.domain.Member;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IMemberDao {

    @Select("select * from member where id = #{id}")
    Member findById(Integer id) throws Exception;

    @Select("select * from member")
    List<Member> findAll() throws Exception;
}
