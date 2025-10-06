package com.city.repository;

import com.city.model.EmergencyAlert;
import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

public class EmergencyRepository {
    private final PriorityQueue<EmergencyAlert> pq = new PriorityQueue<>();

    public synchronized void add(EmergencyAlert e) { pq.add(e); }

    public synchronized EmergencyAlert poll() { return pq.poll(); }

    public synchronized List<EmergencyAlert> listActive() {
        List<EmergencyAlert> res = new ArrayList<>();
        for (EmergencyAlert e : pq) if (e.isActive()) res.add(e);
        return res;
    }
}
