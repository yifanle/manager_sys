package com.muzile.manage_sys.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PageController {

    @RequestMapping("/")
    public String root(){
        return "index";
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/order")
    public String order(){
        return "order";
    }

    @RequestMapping(value = "/product",method = {RequestMethod.GET})
    public String product(){
        return "product";
    }

    @RequestMapping("/loginPage")
    public String login(){
        return "login";
    }

    @RequestMapping("/registPage")
    public String regist(){
        return "regist";
    }

    @RequestMapping("/user")
    public String user(){
        return "users";
    }

    @RequestMapping("/role")
    public String role(){
        return "roles";
    }

    @RequestMapping("/permission")
    public String permission(){
        return "permissions";
    }

    @RequestMapping("/sysLog")
    public String sysLog(){
        return "sysLog";
    }

    @RequestMapping("/stock")
    public String stock(){
        return "stock";
    }
}
