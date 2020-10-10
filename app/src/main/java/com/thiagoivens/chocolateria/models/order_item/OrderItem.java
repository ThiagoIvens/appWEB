package com.thiagoivens.chocolateria.models.order_item;

import com.thiagoivens.chocolateria.models.order.Order;
import com.thiagoivens.chocolateria.models.product.Product;

//  ITEMS NA ORDEM DE COMPRA

public class OrderItem {
    private String url;
    private Integer quantity;
    private String date_added;
    private Product product;
    private Order order;

    public OrderItem(String url, Integer quantity, String date_added, Product product, Order order) {
        this.url = url;
        this.quantity = quantity;
        this.date_added = date_added;
        this.product = product;
        this.order = order;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDate_added() {
        return date_added;
    }

    public void setDate_added(String date_added) {
        this.date_added = date_added;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
