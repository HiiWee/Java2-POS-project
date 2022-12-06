package com.project.controller.dto;

import java.time.LocalDate;

public class TotalSellProductDto {
    private final Long sellId;
    private final LocalDate localDate;
    private final int totalPrice;
    private final long totalQuantity;

    public TotalSellProductDto(final Long sellId, final LocalDate localDate, final int totalPrice,
                               final long totalQuantity) {
        this.sellId = sellId;
        this.localDate = localDate;
        this.totalPrice = totalPrice;
        this.totalQuantity = totalQuantity;
    }

    public Long getSellId() {
        return sellId;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public long getTotalQuantity() {
        return totalQuantity;
    }
}
