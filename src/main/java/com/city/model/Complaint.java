package com.city.model;

import java.time.LocalDateTime;

public class Complaint {
    private final String complaintId;
    private final String citizenId;
    private final String category;
    private final String description;
    private Status status;
    private int priority;
    private final LocalDateTime createdAt;

    public Complaint(String complaintId, String citizenId, String category, String description, int priority) {
        this.complaintId = complaintId;
        this.citizenId = citizenId;
        this.category = category;
        this.description = description;
        this.status = Status.OPEN;
        this.priority = priority;
        this.createdAt = LocalDateTime.now();
    }

    public String getComplaintId() { return complaintId; }
    public String getCitizenId() { return citizenId; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public Status getStatus() { return status; }
    public int getPriority() { return priority; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void setStatus(Status status) { this.status = status; }
    public void setPriority(int priority) { this.priority = priority; }

    @Override
    public String toString() {
        return "Complaint{" + complaintId + " category=" + category + " status=" + status + "}";
    }
}
