package com.qrfeedback.qrbackend.controller;


import com.qrfeedback.qrbackend.DTO.FeedbackRequest;
import com.qrfeedback.qrbackend.Utils.SentimentUtil;
import com.qrfeedback.qrbackend.model.Feedback;
import com.qrfeedback.qrbackend.model.QRCode;
import com.qrfeedback.qrbackend.repository.FeedbackRepository;
import com.qrfeedback.qrbackend.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/feedback")
@CrossOrigin(origins = "http://localhost:5173")
public class FeedbackController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private QRCodeRepository qrCodeRepository;

    @PostMapping
    public ResponseEntity<?> submitFeedback(@RequestBody FeedbackRequest request)
    {
        Optional<QRCode> qrOpt = qrCodeRepository.findById(request.getQrId());
        if(qrOpt.isEmpty()){
            return ResponseEntity.badRequest().body("QR code not found");
        }
        String sentiment = SentimentUtil.analyze(request.getComment());

        Feedback feedback = new Feedback();
        feedback.setId(UUID.randomUUID());
        feedback.setQrCode(qrOpt.get());
        feedback.setRating(request.getRating());
        feedback.setComment(request.getComment());
        feedback.setSentiment(sentiment);
        feedback.setCreatedAt(LocalDateTime.now());

        feedbackRepository.save(feedback);

        return ResponseEntity.ok(Map.of("message","Feedback submitted successfully","sentiment",sentiment));
    }

}
