package com.qrfeedback.qrbackend.controller;


import com.qrfeedback.qrbackend.DTO.*;
import com.qrfeedback.qrbackend.Utils.SentimentUtil;
import com.qrfeedback.qrbackend.model.Feedback;
import com.qrfeedback.qrbackend.model.QRCode;
import com.qrfeedback.qrbackend.repository.FeedbackRepository;
import com.qrfeedback.qrbackend.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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

    @PostMapping("/filter")
    public ResponseEntity<?> filterFeedback(@RequestBody FeedbackFilterRequest filter) {
        List<Feedback> feedbackList = feedbackRepository.filterFeedback(
                filter.getQrId(),
                filter.getSentiments(),
                filter.getStartDate(),
                filter.getEndDate()
        );
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/stats/{qrId}")
    public ResponseEntity<?> getStats(@PathVariable UUID qrId) {
        long total = feedbackRepository.countByQrCodeId(qrId);
        long positive = feedbackRepository.countPositiveByQrCodeId(qrId);
        Double avgRating = feedbackRepository.averageRatingByQrCodeId(qrId);

        double positivePercentage = (total > 0) ? ((double) positive / total) * 100 : 0;

        FeedbackStatsResponse response = new FeedbackStatsResponse(
                total,
                positive,
                (avgRating != null) ? avgRating : 0.0,
                positivePercentage
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/trends/{qrId}")
    public ResponseEntity<?> getTrends(@PathVariable UUID qrId) {
        List<SentimentTrendResponse> trends = feedbackRepository.getSentimentTrends(qrId);
        return ResponseEntity.ok(trends);
    }

    @GetMapping("/distribution/{qrId}")
    public ResponseEntity<?> getDistribution(@PathVariable UUID qrId) {
        Object[] result = feedbackRepository.getSentimentDistribution(qrId);
        SentimentDistributionResponse response = new SentimentDistributionResponse(
                ((Number) result[0]).longValue(),
                ((Number) result[1]).longValue(),
                ((Number) result[2]).longValue()
        );
        return ResponseEntity.ok(response);
    }

    @GetMapping("/average-rating/{qrId}")
    public ResponseEntity<Double> getAverageRating(@PathVariable UUID qrId) {
        Double avg = feedbackRepository.findAverageRatingByQrCodeId(qrId);
        return ResponseEntity.ok(avg != null ? avg : 0.0);
    }

}
