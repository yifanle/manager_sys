package com.muzile.manage_sys.domain;

import java.util.List;

public class ChartData {
    private List<String> labels;
    private List<Object> data;

    public List<String> getLabels() {
        return labels;
    }

    public void setLabels(List<String> labels) {
        this.labels = labels;
    }

    public List<Object> getData() {
        return data;
    }

    public void setData(List<Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ChartData{" +
                "labels=" + labels +
                ", data=" + data +
                '}';
    }
}
