package com.qrfeedback.qrbackend.DTO;

import com.qrfeedback.qrbackend.model.Feedback;
import com.qrfeedback.qrbackend.model.QRCode;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminDashboardResponse {
    private List<QRCode> qrCodes;
    private List<Feedback> feedbacks;

    private Map<String, Long> sentimentCount; // pie chart
    private double averageRating;             // avg rating
    private List<String> urgentComments;      // for attention

    public AdminDashboardResponse(List<QRCode> qrCodes, List<Feedback> feedbacks) {
        this.qrCodes = qrCodes;
        this.feedbacks = feedbacks;

        this.sentimentCount = feedbacks.stream()
                .collect(Collectors.groupingBy(
                        Feedback::getSentiment,
                        Collectors.counting()
                ));

        this.averageRating = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average()
                .orElse(0.0);

        this.urgentComments = feedbacks.stream()
                .filter(f -> "negative".equalsIgnoreCase(f.getSentiment()) && f.getComment() != null)
                .map(Feedback::getComment)
                .collect(Collectors.toList());
    }

    public List<QRCode> getQrCodes() {
        return qrCodes;
    }

    public void setQrCodes(List<QRCode> qrCodes) {
        this.qrCodes = qrCodes;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    public Map<String, Long> getSentimentCount() {
        return sentimentCount;
    }

    public void setSentimentCount(Map<String, Long> sentimentCount) {
        this.sentimentCount = sentimentCount;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<String> getUrgentComments() {
        return urgentComments;
    }

    public void setUrgentComments(List<String> urgentComments) {
        this.urgentComments = urgentComments;
    }
}
