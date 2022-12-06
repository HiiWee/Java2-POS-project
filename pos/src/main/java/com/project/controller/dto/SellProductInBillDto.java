package com.project.controller.dto;

public class SellProductInBillDto {
    private final String productName;
    private final long quantity;
    private final int price;

    public SellProductInBillDto(final String productName, final long quantity, final int price) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public long getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }
}
