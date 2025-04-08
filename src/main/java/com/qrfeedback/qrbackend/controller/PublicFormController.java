package com.qrfeedback.qrbackend.controller;

import com.qrfeedback.qrbackend.model.QRCode;
import com.qrfeedback.qrbackend.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/public")
public class PublicFormController {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @GetMapping("/form/{id}")
    public ResponseEntity<?> getPublicForm(@PathVariable UUID id) {
        Optional<QRCode> qrOpt = qrCodeRepository.findById(id);

        if (qrOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("QR Code not found.");
        }

        QRCode qr = qrOpt.get();

        // 1. Check time expiry
        if (qr.getExpiryTime() != null && qr.getExpiryTime().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(HttpStatus.GONE).body("QR Code expired by time.");
        }

        // 2. Check scan limit
        if (qr.getScanLimit() != null && qr.getCurrentScanCount() >= qr.getScanLimit()) {
            return ResponseEntity.status(HttpStatus.GONE).body("QR Code expired by scan limit.");
        }

        // 3. Increment scan count and save
        qr.setCurrentScanCount(qr.getCurrentScanCount() + 1);
        qrCodeRepository.save(qr);

        // 4. Return success with form data
        return ResponseEntity.ok(qr);
    }
}
