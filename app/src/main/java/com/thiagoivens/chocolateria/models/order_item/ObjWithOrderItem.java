package com.thiagoivens.chocolateria.models.order_item;

import java.util.List;

public class ObjWithOrderItem {
    int count;
    String next;
    String previous;
    List<OrderItem> results;

    public ObjWithOrderItem(int count, String next, String previous, List<OrderItem> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public List<OrderItem> getResults() {
        return results;
    }
}
