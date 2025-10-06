package com.city.service;

import com.city.model.*;
import com.city.repository.EmergencyRepository;
import com.city.exception.EmergencyPriorityException;
import com.city.exception.InvalidInputException;

import java.util.*;

public class EmergencyService {
    private final EmergencyRepository repo;

    public EmergencyService(EmergencyRepository repo) { this.repo = repo; }

    public synchronized EmergencyAlert raiseEmergencyAlert(String alertId, EmergencyType type, String location, EmergencySeverity severity) {
        if (severity == null) throw new EmergencyPriorityException("Severity required");
        EmergencyAlert e = new EmergencyAlert(alertId, type, location, severity);
        repo.add(e);
        return e;
    }

    public synchronized void dispatchEmergencyService(String alertId) {
        // naive dispatch: mark the alert inactive once dispatched
        List<EmergencyAlert> actives = repo.listActive();
        for (EmergencyAlert e : actives) {
            if (e.getAlertId().equals(alertId)) {
                e.deactivate();
                return;
            }
        }
        throw new InvalidInputException("Alert not found");
    }

    public List<EmergencyAlert> getActiveEmergencies() { return repo.listActive(); }
}
