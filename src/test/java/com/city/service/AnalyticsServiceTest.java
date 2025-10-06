package com.city.service;

import com.city.repository.TrafficRepository;
import com.city.repository.ComplaintRepository;
import com.city.model.TrafficSensor;
import com.city.model.UtilityMeter;
import com.city.model.UtilityType;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

public class AnalyticsServiceTest {
    private AnalyticsService analytics;
    private TrafficRepository tRepo;
    private ComplaintRepository cRepo;

    @BeforeMethod
    public void setup() {
        tRepo = new TrafficRepository();
        cRepo = new ComplaintRepository();
        tRepo.add(new TrafficSensor("t1","A"));
        tRepo.findById("t1").ifPresent(s -> s.updateDensity(30));
        HashMap<String, UtilityMeter> meters = new HashMap<>();
        UtilityMeter m = new UtilityMeter("m1","prop1", UtilityType.WATER);
        m.recordReading(100);
        meters.put(m.getMeterId(), m);
        analytics = new AnalyticsService(tRepo, meters, cRepo);
    }

    @Test
    public void testReports() {
        String tr = analytics.generateTrafficReport();
        Assert.assertTrue(tr.contains("Avg Traffic Density"));
        String ur = analytics.generateUtilityConsumptionReport();
        Assert.assertTrue(ur.contains("Total Utility Consumption"));
    }
}
