package com.muzile.manage_sys.domain;

import com.muzile.manage_sys.utils.DateUtils;

import java.util.Date;

public class Metadata {
    private Double dailyPrice;
    private Date chartTime;
    private String chartTimeStr;
    private String label;
    private Integer dailyQuant;

    public String getChartTimeStr() {
        return DateUtils.date2String(chartTime,"yyyy-MM-dd");
    }

    public void setChartTimeStr(String chartTimeStr) {
        this.chartTimeStr = chartTimeStr;
    }

    public Double getDailyPrice() {
        return dailyPrice;
    }

    public void setDailyPrice(Double dailyPrice) {
        this.dailyPrice = dailyPrice;
    }

    public Date getChartTime() {
        return chartTime;
    }

    public void setChartTime(Date chartTime) {
        this.chartTime = chartTime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Integer getDailyQuant() {
        return dailyQuant;
    }

    public void setDailyQuant(Integer dailyQuant) {
        this.dailyQuant = dailyQuant;
    }
}
