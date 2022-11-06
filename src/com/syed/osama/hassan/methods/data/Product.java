package com.syed.osama.hassan.methods.data;

import java.util.Date;

public class Product {
    private String name;
    private double price;
    private long quantity;
    private Date expirationDate;
    private Address address;

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public long getQuantity() {
        return quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
