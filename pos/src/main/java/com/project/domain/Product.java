package com.project.domain;

public class Product {
    private Long id;
    private String name;
    private int price;

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getPrice() {
        return price;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(int price) {
        this.price = price;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
