package com.thiagoivens.chocolateria.models.order;

import java.util.List;

public class ObjWithOrder {
    int count;
    String next;
    String previous;
    List<Order> results;

    public ObjWithOrder(int count, String next, String previous, List<Order> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public List<Order> getResults() {
        return results;
    }
}
