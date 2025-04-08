package com.qrfeedback.qrbackend.DTO;

import lombok.Data;

@Data
public class SentimentTrendResponse {
    private String date;      // e.g. "2025-04-08"
    private long positive;
    private long neutral;
    private long negative;

    public SentimentTrendResponse(String date, long positive, long neutral, long negative) {
        this.date = date;
        this.positive = positive;
        this.neutral = neutral;
        this.negative = negative;
    }
}
