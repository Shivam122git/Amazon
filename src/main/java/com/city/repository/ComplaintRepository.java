package com.city.repository;

import com.city.model.Complaint;
import com.city.exception.DuplicateEntityException;

import java.util.*;

public class ComplaintRepository {
    private final Map<String, Complaint> store = new HashMap<>();

    public void add(Complaint c) {
        if (store.containsKey(c.getComplaintId())) throw new DuplicateEntityException("Complaint exists: " + c.getComplaintId());
        store.put(c.getComplaintId(), c);
    }

    public Optional<Complaint> findById(String id) { return Optional.ofNullable(store.get(id)); }

    public List<Complaint> findAll() { return new ArrayList<>(store.values()); }

    public List<Complaint> findByPriority(int priority) {
        List<Complaint> res = new ArrayList<>();
        for (Complaint c : store.values()) if (c.getPriority() == priority) res.add(c);
        return res;
    }
}
