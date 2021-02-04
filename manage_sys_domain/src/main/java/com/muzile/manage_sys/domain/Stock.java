package com.muzile.manage_sys.domain;

import com.muzile.manage_sys.utils.DateUtils;

import java.util.Date;

public class Stock {
    private Integer id;
    private String productNum;
    private String productName;
    private Date executeTime;
    private String executeTimeStr;
    private Double productPrice;
    private String username;
    private Integer productQuant;
    private String processObj;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getExecuteTime() {
        return executeTime;
    }

    public void setExecuteTime(Date executeTime) {
        this.executeTime = executeTime;
    }

    public String getExecuteTimeStr() {
        return DateUtils.date2String(executeTime,"yyyy-MM-dd HH:mm:ss");
    }

    public void setExecuteTimeStr(String executeTimeStr) {
        this.executeTimeStr = executeTimeStr;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getProductQuant() {
        return productQuant;
    }

    public void setProductQuant(Integer productQuant) {
        this.productQuant = productQuant;
    }

    public String getProcessObj() {
        return processObj;
    }

    public void setProcessObj(String processObj) {
        this.processObj = processObj;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", productNum='" + productNum + '\'' +
                ", productName='" + productName + '\'' +
                ", executeTime=" + executeTime +
                ", productPrice=" + productPrice +
                ", username='" + username + '\'' +
                ", productQuant=" + productQuant +
                ", processObj='" + processObj + '\'' +
                '}';
    }
}
