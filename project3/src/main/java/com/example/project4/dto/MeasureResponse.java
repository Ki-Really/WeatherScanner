package com.example.project4.dto;

import com.example.project4.models.Measure;

import java.util.List;

public class MeasureResponse {
    private List<MeasureDTO> measureList;

    public MeasureResponse(List<MeasureDTO> measureList) {
        this.measureList = measureList;
    }

    public List<MeasureDTO> getMeasureList() {
        return measureList;
    }

    public void setMeasureList(List<MeasureDTO> measureList) {
        this.measureList = measureList;
    }
}
