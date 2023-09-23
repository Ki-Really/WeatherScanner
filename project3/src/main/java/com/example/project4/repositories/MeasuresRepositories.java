package com.example.project4.repositories;

import com.example.project4.models.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasuresRepositories extends JpaRepository<Measure,Integer> {
}
