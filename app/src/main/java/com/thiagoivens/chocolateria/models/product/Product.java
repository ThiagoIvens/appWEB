package com.thiagoivens.chocolateria.models.product;

public class Product {
    String url;
    String name;
    double price;
    boolean digital;
    String image;

    public Product(String url, String name, double price, boolean digital, String image) {
        this.url = url;
        this.name = name;
        this.price = price;
        this.digital = digital;
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setId(String id) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean getDigital() {
        return digital;
    }

    public void setDigital(boolean digital) {
        this.digital = digital;
    }

    public String getImg() {
        return image;
    }

    public void setImg(String image) {
        this.image = image;
    }
}
