package com.phihung.tao247.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne
    private Order order;

    @ManyToOne
    private Product product;

    private String color;
    private int quantity;
    private int price; //
    private int beforeDiscount;
    private Long userId;
    private LocalDateTime deliveryDate;

    public OrderItem() {

    }

    public OrderItem(Long id, Order order, Product product, String color, int quantity, int price, int beforeDiscount, Long userId, LocalDateTime deliveryDate) {
        this.id = id;
        this.order = order;
        this.product = product;
        this.color = color;
        this.quantity = quantity;
        this.price = price;
        this.beforeDiscount = beforeDiscount;
        this.userId = userId;
        this.deliveryDate = deliveryDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
