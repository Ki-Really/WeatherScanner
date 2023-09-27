package com.example.project4.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.hibernate.mapping.Join;

import java.time.LocalDateTime;

@Entity
@Table(name ="Measure")
public class Measure {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "value")
    @NotNull(message="Value should not be empty!")
    private int value;

    @Column(name = "raining")
    @NotNull(message="Raining should not be empty!")
    private boolean raining;


    @ManyToOne
    @JoinColumn(name="sensor_name", referencedColumnName = "name")
    @NotNull
    private Sensor sensor;

    @Column(name="add_time")
    @NotNull
    private LocalDateTime measureAddTime;


    public Measure(int value, boolean raining, Sensor sensor, LocalDateTime measureAddTime) {
        this.value = value;
        this.raining = raining;
        this.sensor = sensor;
        this.measureAddTime = measureAddTime;
    }

    public Measure() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public LocalDateTime getMeasureAddTime() {
        return measureAddTime;
    }

    public void setMeasureAddTime(LocalDateTime measureAddTime) {
        this.measureAddTime = measureAddTime;
    }
}
