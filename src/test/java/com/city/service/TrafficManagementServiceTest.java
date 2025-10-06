package com.city.service;

import com.city.model.TrafficSensor;
import com.city.repository.TrafficRepository;
import com.city.exception.InvalidInputException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TrafficManagementServiceTest {
    private TrafficManagementService service;
    private TrafficRepository repo;

    @BeforeMethod
    public void setup() {
        repo = new TrafficRepository();
        service = new TrafficManagementService(repo);
        repo.add(new TrafficSensor("s1", "Main St"));
        repo.add(new TrafficSensor("s2", "2nd Ave"));
    }

    @Test
    public void testUpdateTrafficDensityValid() {
        service.updateTrafficDensity("s1", 75.5);
        List<TrafficSensor> highs = service.getHighTrafficAreas(50);
        Assert.assertTrue(highs.stream().anyMatch(s -> s.getSensorId().equals("s1")));
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testUpdateTrafficDensityInvalidSensor() {
        service.updateTrafficDensity("noid", 10);
    }
}
