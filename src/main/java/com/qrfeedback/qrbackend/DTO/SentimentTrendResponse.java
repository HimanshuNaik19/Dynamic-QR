package com.qrfeedback.qrbackend.DTO;

import lombok.Getter;

import java.time.LocalDate;

@Getter
public class SentimentTrendResponse {
    private LocalDate date;      // e.g. "2025-04-08"
    private long positive;
    private long neutral;
    private long negative;

    public SentimentTrendResponse(LocalDate date, long positive, long neutral, long negative) {
        this.date = date;
        this.positive = positive;
        this.neutral = neutral;
        this.negative = negative;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPositive(long positive) {
        this.positive = positive;
    }

    public void setNeutral(long neutral) {
        this.neutral = neutral;
    }

    public void setNegative(long negative) {
        this.negative = negative;
    }
}
