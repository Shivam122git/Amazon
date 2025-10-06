package com.city.service;

import com.city.model.*;
import com.city.repository.*;

import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsService {
    private final TrafficRepository trafficRepo;
    private final Map<String, UtilityMeter> meters;
    private final ComplaintRepository complaintRepo;

    public AnalyticsService(TrafficRepository tRepo, Map<String, UtilityMeter> meters, ComplaintRepository cRepo) {
        this.trafficRepo = tRepo;
        this.meters = meters;
        this.complaintRepo = cRepo;
    }

    public String generateTrafficReport() {
        double avg = trafficRepo.findAll().stream().mapToDouble(TrafficSensor::getCurrentTrafficDensity).average().orElse(0.0);
        return "Avg Traffic Density: " + avg;
    }

    public String generateUtilityConsumptionReport() {
        double total = meters.values().stream().mapToDouble(UtilityMeter::getCurrentReading).sum();
        return "Total Utility Consumption: " + total;
    }

    public Map<Integer, Long> getComplaintStatistics() {
        return complaintRepo.findAll().stream().collect(Collectors.groupingBy(Complaint::getPriority, Collectors.counting()));
    }
}
