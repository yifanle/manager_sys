package com.muzile.manage_sys.controller;

import com.muzile.manage_sys.domain.ChartData;
import com.muzile.manage_sys.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/chart")
@ResponseBody
public class ChartController {

    @Autowired
    private IChartService chartService;

    @RequestMapping("/getAreaData")
    public ChartData getAreaData(){
        ChartData chartData = null;
        try {
            chartData = chartService.getAreaData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chartData;
    }

    @RequestMapping("/getBarData")
    public ChartData getBarData(){
        ChartData chartData = null;
        try {
            chartData = chartService.getBarData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chartData;
    }
}
