package com.city.model;

import java.time.LocalDateTime;

public class UtilityMeter {
    private final String meterId;
    private final String propertyId;
    private final UtilityType utilityType;
    private double currentReading;
    private LocalDateTime lastUpdated;

    public UtilityMeter(String meterId, String propertyId, UtilityType utilityType) {
        this.meterId = meterId;
        this.propertyId = propertyId;
        this.utilityType = utilityType;
        this.currentReading = 0.0;
        this.lastUpdated = LocalDateTime.now();
    }

    public String getMeterId() { return meterId; }
    public String getPropertyId() { return propertyId; }
    public UtilityType getUtilityType() { return utilityType; }
    public double getCurrentReading() { return currentReading; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }

    public void recordReading(double reading) {
        this.currentReading = reading;
        this.lastUpdated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "UtilityMeter{" + meterId + " type=" + utilityType + " reading=" + currentReading + "}";
    }
}
