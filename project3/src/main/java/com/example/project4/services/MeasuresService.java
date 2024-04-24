package com.example.project4.services;

import com.example.project4.models.Measure;
import com.example.project4.repositories.MeasuresRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MeasuresService {
    private final MeasuresRepositories measuresRepositories;
    private final SensorsService sensorsService;

    @Autowired
    public MeasuresService(MeasuresRepositories measuresRepositories, SensorsService sensorsService) {
        this.measuresRepositories = measuresRepositories;
        this.sensorsService = sensorsService;
    }

    public List<Measure> findAll() {
        return measuresRepositories.findAll();
    }

    @Transactional
    public void save(Measure measure) {
        System.out.println("222");
        enrichMeasure(measure);
        measuresRepositories.save(measure);
    }

    private void enrichMeasure(Measure measure) {
        measure.setSensor(sensorsService.findBySensorsName(measure.getSensor().getName()).get());
        measure.setMeasureAddTime(LocalDateTime.now());
        System.out.println("333");
    }
}
