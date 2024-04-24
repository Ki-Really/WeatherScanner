package com.example.project4.services;

import com.example.project4.models.Sensor;
import com.example.project4.repositories.SensorsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private final SensorsRepositories sensorsRepositories;

@Autowired
    public SensorsService(SensorsRepositories sensorsRepositories) {
        this.sensorsRepositories = sensorsRepositories;
    }
    @Transactional
    public void save(Sensor sensor){
        sensorsRepositories.save(sensor);
    }

    public Optional<Sensor> findBySensorsName(String name){
        return sensorsRepositories.findByName(name);
    }
}
