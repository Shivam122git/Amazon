package com.city.model;

import java.time.LocalDateTime;

public class TrafficSensor {
    private final String sensorId;
    private String location;
    private double currentTrafficDensity;
    private LocalDateTime timestamp;
    private Status status;

    public TrafficSensor(String sensorId, String location) {
        this.sensorId = sensorId;
        this.location = location;
        this.currentTrafficDensity = 0.0;
        this.timestamp = LocalDateTime.now();
        this.status = Status.ACTIVE;
    }

    public String getSensorId() { return sensorId; }
    public String getLocation() { return location; }
    public double getCurrentTrafficDensity() { return currentTrafficDensity; }
    public java.time.LocalDateTime getTimestamp() { return timestamp; }
    public Status getStatus() { return status; }

    public void updateDensity(double density) {
        this.currentTrafficDensity = density;
        this.timestamp = LocalDateTime.now();
    }

    public void setStatus(Status status) { this.status = status; }

    @Override
    public String toString() {
        return "TrafficSensor{" + sensorId + " @ " + location + " density=" + currentTrafficDensity + "}";
    }
}
