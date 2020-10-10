package com.thiagoivens.chocolateria.models.product;

import java.util.List;

public class ObjWithProduct {
    int count;
    String next;
    String previous;
    List<Product> results;

    public ObjWithProduct(int count, String next, String previous, List<Product> results) {
        this.count = count;
        this.next = next;
        this.previous = previous;
        this.results = results;
    }

    public List<Product> getResults() {
        return results;
    }
}
