package com.syed.osama.hassan.methods.data;

import java.util.Date;

public class Product {
    private String name;
    private double price;
    private long quantity;
    private Date expirationDate;

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
}
