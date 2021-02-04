package com.muzile.manage_sys.controller;

import com.muzile.manage_sys.domain.Member;
import com.muzile.manage_sys.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private IMemberService memberService;

    @RequestMapping("/findAll")
    public List<Member> findAll() {
        List<Member> memberList = null;
        try {
            memberList = memberService.findAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return memberList;
    }
}
