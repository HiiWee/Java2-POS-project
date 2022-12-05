package com.project.domain;

public class SellProduct {
    private Long id;
    private String productName;
    private long quantity;
    private int price;
    private Long productId;
    private Long sellId;

    public SellProduct(final Long id, final String productName, final long quantity, final int price,
                       final Long productId, final Long sellId) {
        this.id = id;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.sellId = sellId;
    }

    public SellProduct(final String productName, final long quantity, final int price, final Long productId,
                       final Long sellId) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.productId = productId;
        this.sellId = sellId;
    }

    public Long getId() {
        return id;
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

    public Long getProductId() {
        return productId;
    }

    public Long getSellId() {
        return sellId;
    }
}
