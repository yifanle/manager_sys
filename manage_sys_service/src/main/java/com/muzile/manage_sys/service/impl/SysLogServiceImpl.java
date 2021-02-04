package com.muzile.manage_sys.service.impl;

import com.github.pagehelper.PageHelper;
import com.muzile.manage_sys.dao.ISysLogDao;
import com.muzile.manage_sys.domain.SysLog;
import com.muzile.manage_sys.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SysLogServiceImpl implements ISysLogService {

    @Autowired
    private ISysLogDao sysLogDao;

    @Override
    public void save(SysLog sysLog) throws Exception {
        sysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer page,Integer size) throws Exception {
        PageHelper.startPage(page,size);
        return sysLogDao.findAll();
    }

    @Override
    public void saveEmail(String email) throws Exception {
        String existEmail = sysLogDao.getEmail();
        if(existEmail!=null&&existEmail!=""){
            sysLogDao.updateEmail(email);
        }else{
            sysLogDao.saveEmail(email);
        }
    }

    @Override
    public String getEmail() throws Exception {
        return sysLogDao.getEmail();
    }
}
