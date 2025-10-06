package com.city;

import com.city.exception.DuplicateEntityException;
import com.city.model.*;
import com.city.repository.*;
import com.city.service.*;

import javax.swing.text.View;
import java.util.List;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {

// Repositories
        CitizenRepository citizenRepo = new CitizenRepository();
        TrafficRepository trafficRepo = new TrafficRepository();
        ComplaintRepository complaintRepo = new ComplaintRepository();
        EmergencyRepository emergencyRepo = new EmergencyRepository();

// Services
        TrafficManagementService trafficService = new TrafficManagementService(trafficRepo);
        UtilityMonitoringService utilityService = new UtilityMonitoringService();
        ComplaintService complaintService = new ComplaintService(complaintRepo);
        EmergencyService emergencyService = new EmergencyService(emergencyRepo);
        AnalyticsService analyticsService = new AnalyticsService(trafficRepo, utilityService.getClass().isInstance(utilityService) ? new java.util.HashMap<>() : new java.util.HashMap<>(), complaintRepo);
        Scanner sc = new Scanner(System.in);

        System.out.println("Smart City Management CLI");

        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1) Register Citizen"); // First
            System.out.println("2) Register Traffic Sensor");
            System.out.println("3) Update Traffic Density");
            System.out.println("4) Register Complaint");
            System.out.println("5) Raise Emergency Alert");
            System.out.println("6) Show Active Emergencies");
            System.out.println("7) View All Complaints");
            System.out.println("8) View All Citizens"); // Before exit
            System.out.println("9) Exit"); // Last
            System.out.print("> ");

            String opt = sc.nextLine();

            try {
                switch (opt) {
                    case "1": {
// Create Citizen
                        System.out.print("Citizen ID: ");
                        String cid = sc.nextLine();
                        System.out.print("Name: ");
                        String name = sc.nextLine();
                        System.out.print("Address: ");
                        String addr = sc.nextLine();
                        System.out.print("Contact Number: ");
                        String contact = sc.nextLine();
                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        try {
                            citizenRepo.createCitizen(cid, name, addr, contact, email);
                        } catch (DuplicateEntityException e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case "2": {
                        System.out.print("Sensor ID: ");
                        String id = sc.nextLine();
                        System.out.print("Location: ");
                        String loc = sc.nextLine();
                        trafficService.registerSensor(new TrafficSensor(id, loc));
                        System.out.println("Sensor registered.");
                        break;
                    }
                    case "3": {
                        System.out.print("Sensor ID: ");
                        String id = sc.nextLine();
                        System.out.print("Density (number): ");
                        double d = Double.parseDouble(sc.nextLine());
                        trafficService.updateTrafficDensity(id, d);
                        System.out.println("Updated.");
                        break;
                    }
                    case "4": {
                        System.out.print("Complaint ID: ");
                        String cid = sc.nextLine();
                        System.out.print("Citizen ID: ");
                        String csc = sc.nextLine();
                        System.out.print("Category: ");
                        String cat = sc.nextLine();
                        System.out.print("Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Priority (1-5): ");
                        int p = Integer.parseInt(sc.nextLine());
                        complaintService.registerComplaint(cid, csc, cat, desc, p);
                        System.out.println("Complaint registered.");
                        break;
                    }
                    case "5": {
                        System.out.print("Alert ID: ");
                        String aid = sc.nextLine();
                        System.out.print("Type (FIRE/MEDICAL/SECURITY): ");
                        EmergencyType et = EmergencyType.valueOf(sc.nextLine().toUpperCase());
                        System.out.print("Location: ");
                        String loc = sc.nextLine();
                        System.out.print("Severity (LOW/MEDIUM/HIGH): ");
                        EmergencySeverity sev = EmergencySeverity.valueOf(sc.nextLine().toUpperCase());
                        emergencyService.raiseEmergencyAlert(aid, et, loc, sev);
                        System.out.println("Emergency raised.");
                        break;
                    }
                    case "6": {
                        List<EmergencyAlert> act = emergencyService.getActiveEmergencies();
                        if (act.isEmpty()) {
                            System.out.println("No active emergencies.");
                        } else {
                            act.forEach(System.out::println);
                        }
                        break;
                    }
                    case "7": {
                        List<Complaint> allComplaints = complaintService.getAllComplaints();
                        if (allComplaints.isEmpty()) {
                            System.out.println("No complaints found.");
                        } else {
                            allComplaints.forEach(System.out::println);
                        }
                        break;
                    }
                    case "8": {
// View all citizens
                        citizenRepo.printAllCitizens();
                        break;
                    }
                    case "9": {
                        System.out.println("Exiting...");
                        sc.close();
                        return;
                    }
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception ex) {
                System.out.println("Error: " + ex.getMessage());
            }
        }
    }
}