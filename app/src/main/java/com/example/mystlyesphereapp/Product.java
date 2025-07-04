package com.example.mystlyesphereapp;

public class Product {
    private String name;
    private double price;
    private String description;
    private String category;

    public Product(String name, double price, String description, String category) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getDescription() { return description; }
    public String getCategory() { return category; }
}
