package com.city.model;

public class PublicService {
    private final String serviceId;
    private final String serviceType;
    private final String location;
    private Status availabilityStatus;

    public PublicService(String serviceId, String serviceType, String location, Status availabilityStatus) {
        this.serviceId = serviceId;
        this.serviceType = serviceType;
        this.location = location;
        this.availabilityStatus = availabilityStatus;
    }

    public String getServiceId() { return serviceId; }
    public String getServiceType() { return serviceType; }
    public String getLocation() { return location; }
    public Status getAvailabilityStatus() { return availabilityStatus; }
    public void setAvailabilityStatus(Status s) { this.availabilityStatus = s; }

    @Override
    public String toString() {
        return "PublicService{" + serviceId + " type=" + serviceType + " status=" + availabilityStatus + "}";
    }
}
