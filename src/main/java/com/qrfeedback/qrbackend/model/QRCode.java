package com.qrfeedback.qrbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name ="qr_code")
@Data
public class QRCode {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String context;

    private LocalDateTime expiryTime;

    private int maxUses;

    private int useCount;

    private LocalDateTime createdAt;

    private  UUID adminId;

    public QRCode() {
    }

    public QRCode(UUID id, String context, LocalDateTime expiryTime, int maxUses, int useCount, LocalDateTime createdAt) {
        this.id = id;
        this.context = context;
        this.expiryTime = expiryTime;
        this.maxUses = maxUses;
        this.useCount = useCount;
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }

    public void setExpiryTime(LocalDateTime expiryTime) {
        this.expiryTime = expiryTime;
    }

    public int getMaxUses() {
        return maxUses;
    }

    public void setMaxUses(int maxUses) {
        this.maxUses = maxUses;
    }

    public int getUseCount() {
        return useCount;
    }

    public void setUseCount(int useCount) {
        this.useCount = useCount;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getAdminId() {
        return adminId;
    }

    public void setAdminId(UUID adminId) {
        this.adminId = adminId;
    }
}
