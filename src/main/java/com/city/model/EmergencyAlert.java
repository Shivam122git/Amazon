package com.city.model;

import java.time.LocalDateTime;

public class EmergencyAlert implements Comparable<EmergencyAlert> {
    private final String alertId;
    private final EmergencyType type;
    private final String location;
    private final EmergencySeverity severity;
    private final LocalDateTime timestamp;
    private boolean active;

    public EmergencyAlert(String alertId, EmergencyType type, String location, EmergencySeverity severity) {
        this.alertId = alertId;
        this.type = type;
        this.location = location;
        this.severity = severity;
        this.timestamp = LocalDateTime.now();
        this.active = true;
    }

    public String getAlertId() { return alertId; }
    public EmergencyType getType() { return type; }
    public String getLocation() { return location; }
    public EmergencySeverity getSeverity() { return severity; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; }

    @Override
    public int compareTo(EmergencyAlert other) {
        return other.severity.ordinal() - this.severity.ordinal(); // HIGH first
    }

    @Override
    public String toString() {
        return "EmergencyAlert{" + alertId + " type=" + type + " severity=" + severity + " loc=" + location + "}";
    }
}
