package com.muzile.manage_sys.service.impl;

import com.muzile.manage_sys.dao.IMemberDao;
import com.muzile.manage_sys.domain.Member;
import com.muzile.manage_sys.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService implements IMemberService {

    @Autowired
    private IMemberDao memberDao;

    @Override
    public List<Member> findAll() throws Exception {
        return memberDao.findAll();
    }
}
