package com.muzile.manage_sys.controller;

import com.github.pagehelper.PageInfo;
import com.muzile.manage_sys.domain.SysLog;
import com.muzile.manage_sys.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@ResponseBody
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private ISysLogService sysLogService;

    @RequestMapping("/findAll")
    public PageInfo findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page, @RequestParam(name = "size",required = true,defaultValue = "5") Integer size){
        PageInfo pageInfo = null;
        try {
            List<SysLog> sysLogList = sysLogService.findAll(page,size);
            pageInfo = new PageInfo(sysLogList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageInfo;
    }

    @RequestMapping("/saveEmail")
    public void saveEmail(String email){
        try {
            sysLogService.saveEmail(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/getEmail")
    public String saveEmail(){
        String email = null;
        try {
            email = sysLogService.getEmail();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return email;
    }
}
