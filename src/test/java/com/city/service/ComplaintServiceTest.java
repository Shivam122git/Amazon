package com.city.service;

import com.city.repository.ComplaintRepository;
import com.city.model.Complaint;
import com.city.exception.InvalidInputException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class ComplaintServiceTest {
    private ComplaintService service;
    private ComplaintRepository repo;

    @BeforeMethod
    public void setup() {
        repo = new ComplaintRepository();
        service = new ComplaintService(repo);
    }

    @Test
    public void testRegisterAndQuery() {
        Complaint c = service.registerComplaint("c1", "cit1", "ROAD", "Pothole", 2);
        List<Complaint> byPriority = service.getComplaintsByPriority(2);
        Assert.assertEquals(byPriority.size(), 1);
        Assert.assertEquals(byPriority.get(0).getComplaintId(), "c1");
    }

    @Test(expectedExceptions = InvalidInputException.class)
    public void testRegisterInvalidId() {
        service.registerComplaint("", "cit1", "ROAD", "desc", 1);
    }
}
