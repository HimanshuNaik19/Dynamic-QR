package com.qrfeedback.qrbackend.DTO;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
public class FeedbackFilterRequest {
    private UUID qrId;
    private List<String> sentiments; // "positive", "neutral", "negative"
    private LocalDate startDate;
    private LocalDate endDate;

    public UUID getQrId() {
        return qrId;
    }

    public void setQrId(UUID qrId) {
        this.qrId = qrId;
    }

    public List<String> getSentiments() {
        return sentiments;
    }

    public void setSentiments(List<String> sentiments) {
        this.sentiments = sentiments;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}
