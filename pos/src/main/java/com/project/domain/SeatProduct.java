package com.project.domain;

public class SeatProduct {
    private Long id;
    private final long quantity;
    private final int price;
    private final Long productId;
    private final String productName;
    private final Long seatId;

    public SeatProduct(final Long id, final long quantity, final int price, final Long productId,
                       final String productName, final Long seatId) {
        this.id = id;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.productName = productName;
        this.seatId = seatId;
    }

    public SeatProduct(final long quantity, final int price, final Long productId, final String productName,
                       final Long seatId) {
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.productName = productName;
        this.seatId = seatId;
    }

    public Long getId() {
        return id;
    }

    public long getQuantity() {
        return quantity;
    }

    public int getPrice() {
        return price;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getSeatId() {
        return seatId;
    }

    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return "SeatProduct{" +
                "id=" + id +
                ", quantity=" + quantity +
                ", price=" + price +
                ", productId=" + productId +
                ", productName='" + productName + '\'' +
                ", seatId=" + seatId +
                '}';
    }
}
