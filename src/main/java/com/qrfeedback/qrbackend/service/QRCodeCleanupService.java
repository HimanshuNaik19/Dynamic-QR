package com.qrfeedback.qrbackend.service;

import com.qrfeedback.qrbackend.model.QRCode;
import com.qrfeedback.qrbackend.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class QRCodeCleanupService {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @Scheduled(cron = "0 0 * * * *") // every hour
    public void cleanUpExpiredQRCodes() {
        LocalDateTime now = LocalDateTime.now();
        List<QRCode> allQRCodes = qrCodeRepository.findAll();

        List<QRCode> expired = allQRCodes.stream()
                .filter(qr -> qr.getExpiryTime().isBefore(now) || qr.getUseCount() >= qr.getMaxUses())
                .collect(Collectors.toList());

        if (!expired.isEmpty()) {
            qrCodeRepository.deleteAll(expired);
            System.out.println("Deleted " + expired.size() + " expired QR codes");
        }
    }
}

