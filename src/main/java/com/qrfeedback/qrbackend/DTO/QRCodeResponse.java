package com.qrfeedback.qrbackend.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;


public class QRCodeResponse {
    private UUID id;
    private String url;
    private LocalDateTime createdAt;
    private LocalDateTime expiryTime;
    private int maxUses;

    public QRCodeResponse(UUID id, String url, LocalDateTime createdAt, LocalDateTime expiryTime, int maxUses) {
        this.id = id;
        this.url = url;
        this.createdAt = createdAt;
        this.expiryTime = expiryTime;
        this.maxUses = maxUses;
    }
}
