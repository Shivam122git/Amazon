package com.city.repository;

import com.city.model.Citizen;
import com.city.exception.DuplicateEntityException;

import java.util.*;

public class CitizenRepository {

    private final Map<String, Citizen> store = new HashMap<>();

    // Existing method: add a Citizen object
    public void add(Citizen c) {
        if (store.containsKey(c.getCitizenId())) {
            throw new DuplicateEntityException("Citizen exists: " + c.getCitizenId());
        }
        store.put(c.getCitizenId(), c);
        System.out.println("Citizen added successfully: " + c);
    }

    // Existing method: find by ID
    public Optional<Citizen> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    // Existing method: list all citizens
    public List<Citizen> findAll() {
        return new ArrayList<>(store.values());
    }

    // 🔹 NEW helper method to create citizen from fields
    public void createCitizen(String citizenId, String name, String address, String contactNumber, String email) {
        Citizen c = new Citizen(citizenId, name, address, contactNumber, email);
        add(c); // reuse existing add() method
    }

    // Optional: print all citizens
    public void printAllCitizens() {
        System.out.println("------ All Citizens ------");
        if (store.isEmpty()) {
            System.out.println("No citizens found.");
        } else {
            for (Citizen c : store.values()) {
                System.out.println(c); // calls Citizen.toString()
            }
        }
        System.out.println("--------------------------");
    }
}