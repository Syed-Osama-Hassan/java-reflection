package com.syed.osama.hassan.methods.data;

public class Cloth extends Product {
    private String color;
    private Size size;

    public Size getSize() {
        return size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
