package com.qrfeedback.qrbackend.DTO;

import lombok.Data;

@Data
public class SentimentDistributionResponse {
    private long positive;
    private long neutral;
    private long negative;

    public SentimentDistributionResponse(long positive, long neutral, long negative) {
        this.positive = positive;
        this.neutral = neutral;
        this.negative = negative;
    }
}
