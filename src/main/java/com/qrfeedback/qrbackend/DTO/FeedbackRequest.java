package com.qrfeedback.qrbackend.DTO;


import java.util.UUID;

public class FeedbackRequest {
    private UUID qrId;
    private int rating;
    private String comment;

    public UUID getQrId() {
        return qrId;
    }

    public void setQrId(UUID qrId) {
        this.qrId = qrId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
