package com.thiagoivens.chocolateria.models.shipping_address;

import com.thiagoivens.chocolateria.models.order.Order;
import com.thiagoivens.chocolateria.models.user.User;

public class ShippingAddress {
    private User customer;
    private Order order;
    private String address;
    private String city;
    private String state;
    private String zipcode;
    private String date_added;
}
