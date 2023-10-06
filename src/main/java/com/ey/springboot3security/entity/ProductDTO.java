package com.ey.springboot3security.entity;


public class ProductDTO {

    private String name;
    private float price;

    // Constructors, getters, and setters

    public ProductDTO() {
    }

    public ProductDTO(String name, float price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
