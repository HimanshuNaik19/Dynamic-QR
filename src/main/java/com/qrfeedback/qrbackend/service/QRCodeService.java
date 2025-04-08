package com.qrfeedback.qrbackend.service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.qrfeedback.qrbackend.model.QRCode;
import com.qrfeedback.qrbackend.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;

@Service
public class QRCodeService {

    @Autowired
    private QRCodeRepository qrCodeRepository;

    public QRCode generateAndSaveQRCode(String context , int maxUses, LocalDateTime expiryTime) throws Exception {
        QRCode qr = new QRCode();
        qr.setContext(context);
        qr.setMaxUses(maxUses);
        qr.setExpiryTime(expiryTime);
        qr.setCreatedAt(LocalDateTime.now());

        return qrCodeRepository.save(qr);
    }

    private byte[] createQRImage(String data,int width,int height) throws Exception {
        BitMatrix matrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE,width,height);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        MatrixToImageWriter.writeToStream(matrix,"PNG",outputStream);
        return outputStream.toByteArray();
    }
}
