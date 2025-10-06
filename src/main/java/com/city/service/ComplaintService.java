package com.city.service;

import com.city.model.Complaint;
import com.city.model.Status;
import com.city.repository.ComplaintRepository;
import com.city.exception.InvalidInputException;

import java.util.*;
import java.util.stream.Collectors;

public class ComplaintService {
    private final ComplaintRepository repo;

    public ComplaintService(ComplaintRepository repo) {
        this.repo = repo;
    }

    public Complaint registerComplaint(String complaintId, String citizenId, String category, String description, int priority) {
        if (complaintId == null || complaintId.isBlank()) throw new InvalidInputException("Invalid id");
        Complaint c = new Complaint(complaintId, citizenId, category, description, priority);
        repo.add(c);
        return c;
    }

    public void updateComplaintStatus(String complaintId, String status) {
        Complaint c = repo.findById(complaintId).orElseThrow(() -> new InvalidInputException("Not found"));
        try {
            c.setStatus(Enum.valueOf(com.city.model.Status.class, status));
        } catch (IllegalArgumentException ex) {
            throw new InvalidInputException("Invalid status");
        }
    }

    public List<Complaint> getComplaintsByPriority(int priority) {
        return repo.findByPriority(priority);
    }
    public List<Complaint> getAllComplaints() {
        return repo.findAll();
    }
    public List<Complaint> getComplaintsByStatus(String status) {
        try {
            Status s = Enum.valueOf(com.city.model.Status.class, status.toUpperCase());
            return repo.findAll().stream()
                    .filter(c -> c.getStatus() ==s)
                    .collect(Collectors.toList());
        }
        catch(IllegalArgumentException e) {
            throw new InvalidInputException("Invalid status. Valid values are:OPEN, RESOLVED, CLOSED");

        }
    }
}

