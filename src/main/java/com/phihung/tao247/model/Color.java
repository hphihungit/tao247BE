package com.phihung.tao247.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Color {
    private String name;
    private int quantity;

    public Color() {

    }

    public Color(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
