package com.phihung.tao247.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private String title;


    private String description;


    private int beforeDiscount;

    @Column(name = "price")
    private int price;

    @Column(name = "percents_discount")
    private int percentsDiscount;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "brand")
    private String brand;

    @Column(name = "capacity")
    private int capacity;

    @ElementCollection
    @CollectionTable(name = "product_color", joinColumns = @JoinColumn(name = "product_id"))
    private Set<Color> color = new HashSet<>();

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rating> ratings = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @Column(name = "num_ratings")
    private int numRatings;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    private LocalDateTime createdAt;

    public Product() {

    }

    public Product(Long id, String title, String description, int price, int beforeDiscount, int percentsDiscount, int quantity, String brand, int capacity, Set<Color> color, String imageUrl, List<Rating> ratings, List<Review> reviews, int numRatings, Category category, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.beforeDiscount = beforeDiscount;
        this.percentsDiscount = percentsDiscount;
        this.quantity = quantity;
        this.brand = brand;
        this.capacity = capacity;
        this.color = color;
        this.imageUrl = imageUrl;
        this.ratings = ratings;
        this.reviews = reviews;
        this.numRatings = numRatings;
        this.category = category;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Set<Color> getColor() {
        return color;
    }

    public void setColor(Set<Color> color) {
        this.color = color;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public void setRatings(List<Rating> ratings) {
        this.ratings = ratings;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public int getNumRatings() {
        return numRatings;
    }

    public void setNumRatings(int numRatings) {
        this.numRatings = numRatings;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
