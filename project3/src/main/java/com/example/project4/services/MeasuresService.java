package com.example.project4.services;

import com.example.project4.dto.MeasureDTO;
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

    @Autowired
    public MeasuresService(MeasuresRepositories measuresRepositories) {
        this.measuresRepositories = measuresRepositories;
    }

    public List<Measure> findAll() {
        return measuresRepositories.findAll();
    }

    public Measure findOne(int id) {
        return measuresRepositories.findById(id).orElse(null);
    }
    @Transactional
    public void save(Measure measure) {
        enrichMeasure(measure);
        measuresRepositories.save(measure);
    }

    private void enrichMeasure(Measure measure) {
        measure.setMeasureAddTime(LocalDateTime.now());
    }
}
