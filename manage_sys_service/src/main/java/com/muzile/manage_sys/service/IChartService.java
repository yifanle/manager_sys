package com.muzile.manage_sys.service;

import com.muzile.manage_sys.domain.ChartData;

public interface IChartService {

    ChartData getAreaData() throws Exception;

    ChartData getBarData() throws Exception;
}
