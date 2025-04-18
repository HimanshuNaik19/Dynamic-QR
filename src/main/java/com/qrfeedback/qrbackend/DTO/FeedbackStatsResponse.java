package com.qrfeedback.qrbackend.DTO;

import lombok.Data;

public class FeedbackStatsResponse {
    private long totalFeedback;
    private long positiveCount;
    private double averageRating;
    private double positivePercentage;

    public FeedbackStatsResponse(long totalFeedback, long positiveCount, double averageRating, double positivePercentage) {
        this.totalFeedback = totalFeedback;
        this.positiveCount = positiveCount;
        this.averageRating = averageRating;
        this.positivePercentage = positivePercentage;
    }
}
