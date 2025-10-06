package com.city.service;

import com.city.model.TrafficSensor;
import com.city.repository.TrafficRepository;
import com.city.exception.InvalidInputException;

import java.util.*;
import java.util.stream.Collectors;

public class TrafficManagementService {
    private final TrafficRepository repo;

    public TrafficManagementService(TrafficRepository repo) { this.repo = repo; }

    public void registerSensor(TrafficSensor s) { repo.add(s); }

    public void updateTrafficDensity(String sensorId, double density) {
        TrafficSensor s = repo.findById(sensorId).orElseThrow(() -> new InvalidInputException("Sensor not found"));
        if (density < 0) throw new InvalidInputException("Density can't be negative");
        s.updateDensity(density);
    }

    public List<TrafficSensor> getHighTrafficAreas(double threshold) {
        return repo.findAll().stream().filter(s -> s.getCurrentTrafficDensity() >= threshold).collect(Collectors.toList());
    }

    public void optimizeTrafficSignals(String area) {
        // placeholder for algorithmic optimization
        System.out.println("Optimizing signals in " + area);
    }
}
