package com.muzile.manage_sys.service;

import com.muzile.manage_sys.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    void save(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page,Integer size) throws Exception;

    void saveEmail(String email) throws Exception;

    String getEmail()  throws Exception;;
}
