package com.muzile.manage_sys.domain;

import com.muzile.manage_sys.utils.DateUtils;

import java.util.Date;

public class SysLog {
    private Integer id;
    private Date visitTime;
    private String visitTimeStr;
    private String username;
    private String ip;
    private String ipStr;
    private String url;
    private long executionTime;
    private String method;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getVisitTime() {
        return visitTime;
    }

    public void setVisitTime(Date visitTime) {
        this.visitTime = visitTime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getIpStr() {
        if(ip=="0:0:0:0:0:0:0:1")
            ipStr="localhost";
        else
            ipStr = ip;
        return ipStr;
    }

    public void setIpStr(String ipStr) {
        this.ipStr = ipStr;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(long executionTime) {
        this.executionTime = executionTime;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getVisitTimeStr() {
        return DateUtils.date2String(visitTime,"yyyy-MM-dd HH:mm:ss");
    }

    public void setVisitTimeStr(String visitTimeStr) {
        this.visitTimeStr = visitTimeStr;
    }

    @Override
    public String toString() {
        return "SysLog{" +
                "id=" + id +
                ", visitTime=" + visitTime +
                ", username='" + username + '\'' +
                ", ip='" + ip + '\'' +
                ", url='" + url + '\'' +
                ", executionTime='" + executionTime + '\'' +
                ", method='" + method + '\'' +
                '}';
    }
}
