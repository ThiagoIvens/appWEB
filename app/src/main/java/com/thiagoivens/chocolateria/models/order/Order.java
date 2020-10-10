package com.thiagoivens.chocolateria.models.order;

import com.thiagoivens.chocolateria.models.user.User;

public class Order {
    private User customer;
    private String date_ordered;
    private boolean complete;
    private String transaction_id;

    public Order(User customer, boolean complete) {
        this.customer = customer;
        this.date_ordered = date_ordered;
        this.complete = complete;
        this.transaction_id = transaction_id;
    }
}
