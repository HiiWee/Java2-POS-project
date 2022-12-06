package com.project.domain;

import java.time.LocalDate;

public class Sell {
    private Long id;
    private LocalDate date;
    private Long memberId;

    public Sell(final Long id, final LocalDate date, final Long memberId) {
        this.id = id;
        this.date = date;
        this.memberId = memberId;
    }

    public Sell(final Long memberId) {
        this.memberId = memberId;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Long getMemberId() {
        return memberId;
    }
}
