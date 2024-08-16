package com.phihung.tao247.request;

import com.phihung.tao247.model.Color;

import java.util.HashSet;
import java.util.Set;

public class CreateProductRequest {
    private String brand;
    private String description;
    private int price;
    private int beforeDiscount;
    private int percentsDiscount;
    private int quantity;
    private int capacity;
    private Set<Color> colors = new HashSet<>();
    private String imageUrl;
    private String topLevelCategory;
    private String secondLevelCategory;
    private String thirdLevelCategory;



    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBeforeDiscount() {
        return beforeDiscount;
    }

    public void setBeforeDiscount(int beforeDiscount) {
        this.beforeDiscount = beforeDiscount;
    }

    public int getPercentsDiscount() {
        return percentsDiscount;
    }

    public void setPercentsDiscount(int percentsDiscount) {
        this.percentsDiscount = percentsDiscount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Color> getColors() {
        return colors;
    }

    public void setColors(Set<Color> colors) {
        this.colors = colors;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTopLevelCategory() {
        return topLevelCategory;
    }

    public void setTopLevelCategory(String topLevelCategory) {
        this.topLevelCategory = topLevelCategory;
    }

    public String getSecondLevelCategory() {
        return secondLevelCategory;
    }

    public void setSecondLevelCategory(String secondLevelCategory) {
        this.secondLevelCategory = secondLevelCategory;
    }

    public String getThirdLevelCategory() {
        return thirdLevelCategory;
    }

    public void setThirdLevelCategory(String thirdLevelCategory) {
        this.thirdLevelCategory = thirdLevelCategory;
    }
}
