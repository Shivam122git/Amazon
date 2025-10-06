package com.city.repository;

import com.city.model.TrafficSensor;
import com.city.exception.DuplicateEntityException;

import java.util.*;

public class TrafficRepository {
    private final Map<String, TrafficSensor> store = new HashMap<>();

    public void add(TrafficSensor s) {
        if (store.containsKey(s.getSensorId())) throw new DuplicateEntityException("Sensor exists: " + s.getSensorId());
        store.put(s.getSensorId(), s);
    }

    public Optional<TrafficSensor> findById(String id) { return Optional.ofNullable(store.get(id)); }

    public List<TrafficSensor> findAll() { return new ArrayList<>(store.values()); }
}
