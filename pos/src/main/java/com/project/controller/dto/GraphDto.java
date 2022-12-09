package com.project.controller.dto;

import java.time.LocalDate;

public class GraphDto {
    private String name;
    private int quantity;
    private int price;
    private LocalDate date;
    public GraphDto(int price, LocalDate date) {
        this.price = price;
        this.date = date;
    }

    public GraphDto(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public LocalDate getDate() {
        return date;
    }

}
