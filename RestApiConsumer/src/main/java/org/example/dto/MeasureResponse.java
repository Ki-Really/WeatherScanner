package org.example.dto;

import java.util.List;

public class MeasureResponse {
    private List<MeasureDTO> measureList;

    public MeasureResponse(){}

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
