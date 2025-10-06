package com.city.model;

public class Citizen {
    private final String citizenId;
    private String name;
    private String address;
    private String contactNumber;
    private String email;

    public Citizen(String citizenId, String name, String address, String contactNumber, String email) {
        this.citizenId = citizenId;
        this.name = name;
        this.address = address;
        this.contactNumber = contactNumber;
        this.email = email;
    }

    public String getCitizenId() { return citizenId; }
    public String getName() { return name; }
    public String getAddress() { return address; }
    public String getContactNumber() { return contactNumber; }
    public String getEmail() { return email; }

    public void setName(String name) { this.name = name; }
    public void setAddress(String address) { this.address = address; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return "Citizen{" + "id='" + citizenId + "', name='" + name + "'}";
    }
}
