package com.muzile.manage_sys.controller;

import com.muzile.manage_sys.domain.SysLog;
import com.muzile.manage_sys.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    private Date visitTime;
    private Class clazz;
    private Method method;
    private String ip;
    private String url;
    private String methodName;
    private String username;
    //url、methodName、username、ip、executionTime

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private ISysLogService sysLogService;

    @Before("execution(* com.muzile.manage_sys.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        //访问时间
        visitTime = new Date();
        //获得访问方法：
            //①先获得访问类
        clazz = jp.getTarget().getClass();
            //②获得所访问的方法method
        methodName = jp.getSignature().getName();//得到name根据name反射出方法
            //判断方法是否有参数
        Object[] args = jp.getArgs();//获取访问方法的参数
        if(args == null||args.length == 0){
            method = clazz.getMethod(methodName);
        }else{
            Class[] params = new Class[args.length];
            for(int i = 0 ; i < args.length ; i++){
                params[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,params);
        }
    }

    @After("execution(* com.muzile.manage_sys.controller.*.*(..))")
    public void doAfter(JoinPoint jp){
        long executionTime = new Date().getTime() - visitTime.getTime();
        if(clazz!=null && method!=null && clazz != LogAop.class && clazz != SysLogController.class && methodName != "getCurrentUser" && methodName != "login"){
            //获取url
            //获取类上的@RequestMapping("/product")
            RequestMapping classAnnotation = (RequestMapping)clazz.getAnnotation(RequestMapping.class);
            //获取方法上的“/findAll"
            RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
            if(methodAnnotation!=null){
                String[] methodValues = methodAnnotation.value();
                if(classAnnotation!=null){
                    String[] classValues = classAnnotation.value();
                    url = classValues[0] + methodValues[0];
                }else{
                    url = methodValues[0];
                }

                if(methodValues[0]!="/loginPage") {
                    //获取ip
                    ip = request.getRemoteAddr();
                    //获取username
                    SecurityContext context = SecurityContextHolder.getContext();//从上下文中获了当前登录的用户
                    User user = (User) context.getAuthentication().getPrincipal();
                    username = user.getUsername();

                    //将信息封装到SysLog对象中
                    SysLog sysLog = new SysLog();
                    sysLog.setVisitTime(visitTime);
                    sysLog.setExecutionTime(executionTime);
                    sysLog.setUsername(username);
                    sysLog.setIp(ip);
                    sysLog.setUrl(url);
                    sysLog.setMethod("[类名:]"+clazz.getName()+"[方法名:]"+method.getName());

                    //保存
                    try {
                        sysLogService.save(sysLog);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
