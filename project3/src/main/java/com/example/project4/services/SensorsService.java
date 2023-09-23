package com.example.project4.services;

import com.example.project4.dto.SensorDTO;
import com.example.project4.models.Sensor;
import com.example.project4.repositories.SensorsRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class SensorsService {
    private final SensorsRepositories sensorsRepositories;

@Autowired
    public SensorsService(SensorsRepositories sensorsRepositories) {
        this.sensorsRepositories = sensorsRepositories;
    }

    public List<Sensor> findAll(){
        return sensorsRepositories.findAll();
    }

    public Sensor findOne(int id){
        return sensorsRepositories.findById(id).orElse(null);
    }
    @Transactional
    public void save(Sensor sensor){
        sensorsRepositories.save(sensor);
    }

    public Optional<Sensor> findBySensorsName(String name){
        return sensorsRepositories.findByName(name);
    }


}
