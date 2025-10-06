package com.city.repository;

import com.city.model.Citizen;
import com.city.exception.DuplicateEntityException;

import java.util.*;

public class CitizenRepository {
    private final Map<String, Citizen> store = new HashMap<>();

    public void add(Citizen c) {
        if (store.containsKey(c.getCitizenId())) throw new DuplicateEntityException("Citizen exists: " + c.getCitizenId());
        store.put(c.getCitizenId(), c);
    }

    public Optional<Citizen> findById(String id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<Citizen> findAll() { return new ArrayList<>(store.values()); }
}
