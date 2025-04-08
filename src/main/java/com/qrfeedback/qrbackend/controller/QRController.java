package com.qrfeedback.qrbackend.controller;

import com.qrfeedback.qrbackend.DTO.QRRequest;
import com.qrfeedback.qrbackend.DTO.QRCodeResponse;
import com.qrfeedback.qrbackend.model.QRCode;
import com.qrfeedback.qrbackend.service.QRCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/qrcode")
public class QRController {

    @Autowired
    private QRCodeService qrCodeService;

    @PostMapping
    public ResponseEntity<QRCodeResponse> generateQRCode(@RequestBody QRRequest qrRequest) throws Exception {
        QRCode qr = qrCodeService.generateAndSaveQRCode(
                    qrRequest.getContext(),
                    qrRequest.getMaxUses(),
                    qrRequest.getExpiryTime()
        );

        String frontendUrl =""+qr.getId();

        QRCodeResponse response = new QRCodeResponse(
                qr.getId(),
                frontendUrl,
                qr.getCreatedAt(),
                qr.getExpiryTime(),
                qr.getMaxUses()
        );

        return ResponseEntity.ok(response);
    }
}
