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
    public Product(Long id,String name,int price){
    this.id=id;
    this.name=name;
    this.price=price;
   }
    public Product(String name,int price){
        this.name=name;
        this.price=price;
    }
}
