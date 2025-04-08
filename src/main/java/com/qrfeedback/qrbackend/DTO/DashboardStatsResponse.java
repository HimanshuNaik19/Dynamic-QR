package com.qrfeedback.qrbackend.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class DashboardStatsResponse {
    private double averageRating;
    private int totalFeedback;
    private Map<String, Long> sentimentCounts;
    private List<DailyStats> dailyFeedbackStats;

    @Data
    public static class DailyStats{
        private LocalDate date;
        private double averageRating;

        public LocalDate getDate() {
            return date;
        }

        public void setDate(LocalDate date) {
            this.date = date;
        }

        public double getAverageRating() {
            return averageRating;
        }

        public void setAverageRating(double averageRating) {
            this.averageRating = averageRating;
        }
    }


    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public int getTotalFeedback() {
        return totalFeedback;
    }

    public void setTotalFeedback(int totalFeedback) {
        this.totalFeedback = totalFeedback;
    }

    public Map<String, Long> getSentimentCounts() {
        return sentimentCounts;
    }

    public void setSentimentCounts(Map<String, Long> sentimentCounts) {
        this.sentimentCounts = sentimentCounts;
    }

    public List<DailyStats> getDailyFeedbackStats() {
        return dailyFeedbackStats;
    }

    public void setDailyFeedbackStats(List<DailyStats> dailyFeedbackStats) {
        this.dailyFeedbackStats = dailyFeedbackStats;
    }
}
