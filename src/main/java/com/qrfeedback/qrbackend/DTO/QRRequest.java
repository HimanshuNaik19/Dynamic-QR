package com.qrfeedback.qrbackend.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

public class QRRequest {
    private String context;
    private int maxUses;
    private LocalDateTime expiryTime;
    private UUID adminLinkId;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public UUID getAdminLinkId() {
        return adminLinkId;
    }

    public void setAdminLinkId(UUID adminLinkId) {
        this.adminLinkId = adminLinkId;
    }
}
