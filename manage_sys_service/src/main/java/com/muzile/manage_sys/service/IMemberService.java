package com.muzile.manage_sys.service;

import com.muzile.manage_sys.domain.Member;

import java.util.List;

public interface IMemberService {
    List<Member> findAll() throws Exception;
}
