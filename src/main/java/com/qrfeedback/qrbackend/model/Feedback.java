package com.qrfeedback.qrbackend.model;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import java.text.Normalizer;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "feedback")
@Data
public class Feedback {
    @Id
    @GeneratedValue
    private UUID id;

    // This is the key part!
    @ManyToOne
    @JoinColumn(name = "form_id")  // must match your DB column
    private Form form;

    @ManyToOne
    @JoinColumn(name = "qr_id")
    private QRCode qrCode;

    private int rating;

    private String comment;

    private String sentiment;

    private LocalDateTime createdAt;

    public Feedback() {
    }

    public Feedback(UUID id, QRCode qrCode, int rating, String comment, String sentiment, LocalDateTime createdAt) {
        this.id = id;
        this.qrCode = qrCode;
        this.rating = rating;
        this.comment = comment;
        this.sentiment = sentiment;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public QRCode getQrCode() {
        return qrCode;
    }

    public void setQrCode(QRCode qrCode) {
        this.qrCode = qrCode;
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

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
