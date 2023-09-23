package com.example.project4.repositories;

import com.example.project4.models.Sensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SensorsRepositories extends JpaRepository<Sensor, Integer> {
    Optional<Sensor> findByName(String name);
}
