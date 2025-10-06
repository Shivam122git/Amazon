package com.city.service;

import com.city.model.UtilityMeter;
import com.city.model.UtilityType;
import com.city.repository.*;
import com.city.exception.InvalidInputException;

import java.util.*;

public class UtilityMonitoringService {
    private final Map<String, UtilityMeter> meters = new HashMap<>();

    public void registerMeter(UtilityMeter m) { meters.put(m.getMeterId(), m); }

    public void recordUtilityConsumption(String meterId, double reading) {
        UtilityMeter m = meters.get(meterId);
        if (m == null) throw new InvalidInputException("Meter not found");
        if (reading < 0) throw new InvalidInputException("Reading invalid");
        m.recordReading(reading);
    }

    public double generateBill(String propertyId, UtilityType type) {
        // simplistic billing: sum meters for property and type * rate
        double rate;
        switch(type) {
            case WATER :
                rate = 1.5;
                break;
            case ELECTRICITY:
                rate =  5.0;
                break;
            case GAS:
                rate = 2.0;
                break;
            default:
                throw new InvalidInputException("Unknown utility type:");
        };
        return meters.values().stream()
                .filter(m -> m.getPropertyId().equals(propertyId) && m.getUtilityType() == type)
                .mapToDouble(UtilityMeter::getCurrentReading).sum() * rate;
    }

    public List<UtilityMeter> detectAnomalies(double threshold) {
        List<UtilityMeter> res = new ArrayList<>();
        for (UtilityMeter m : meters.values()) if (m.getCurrentReading() >= threshold) res.add(m);
        return res;
    }
}
