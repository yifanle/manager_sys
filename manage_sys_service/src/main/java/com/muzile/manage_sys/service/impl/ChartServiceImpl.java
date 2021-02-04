package com.muzile.manage_sys.service.impl;

import com.muzile.manage_sys.dao.IChartDao;
import com.muzile.manage_sys.domain.ChartData;
import com.muzile.manage_sys.domain.Metadata;
import com.muzile.manage_sys.service.IChartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class ChartServiceImpl implements IChartService {

    @Autowired
    private IChartDao chartDao;

    @Override
    public ChartData getAreaData() throws Exception {
        List<Metadata> metadataList = chartDao.getAreaData();
        ChartData chartData = new ChartData();
        List<String> labels = new ArrayList<>();
        List<Object> data = new ArrayList<>();
        for(Metadata metadata : metadataList){
            labels.add(metadata.getChartTimeStr());
            data.add(metadata.getDailyPrice());
        }
        chartData.setLabels(labels);
        chartData.setData(data);
        return chartData;
    }

    @Override
    public ChartData getBarData() throws Exception {
        List<Metadata> metadataList = chartDao.getBarData();
        ChartData chartData = new ChartData();
        List<String> labels = new ArrayList<>();
        List<Object> data = new ArrayList<>();
        for(Metadata metadata : metadataList){
            labels.add(metadata.getLabel());
            data.add(metadata.getDailyQuant());
        }
        chartData.setLabels(labels);
        chartData.setData(data);
        return chartData;
    }
}
