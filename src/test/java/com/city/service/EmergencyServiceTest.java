package com.city.service;

import com.city.model.EmergencySeverity;
import com.city.model.EmergencyType;
import com.city.repository.EmergencyRepository;
import com.city.model.EmergencyAlert;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class EmergencyServiceTest {
    private EmergencyService service;
    private EmergencyRepository repo;

    @BeforeMethod
    public void setup() {
        repo = new EmergencyRepository();
        service = new EmergencyService(repo);
    }

    @Test
    public void testRaiseAndList() {
        EmergencyAlert e = service.raiseEmergencyAlert("e1", EmergencyType.FIRE, "Loc1", EmergencySeverity.HIGH);
        List<EmergencyAlert> act = service.getActiveEmergencies();
        Assert.assertTrue(act.stream().anyMatch(a -> a.getAlertId().equals("e1")));
    }

    @Test(expectedExceptions = RuntimeException.class)
    public void testRaiseNullSeverity() {
        service.raiseEmergencyAlert("e2", EmergencyType.MEDICAL, "Loc2", null);
    }
}
