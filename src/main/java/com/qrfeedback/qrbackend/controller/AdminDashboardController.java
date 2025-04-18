package com.qrfeedback.qrbackend.controller;

import com.qrfeedback.qrbackend.DTO.AdminDashboardResponse;
import com.qrfeedback.qrbackend.DTO.DashboardStatsResponse;
import com.qrfeedback.qrbackend.Utils.DashboardUtil;
import com.qrfeedback.qrbackend.model.Feedback;
import com.qrfeedback.qrbackend.model.QRCode;
import com.qrfeedback.qrbackend.repository.FeedbackRepository;
import com.qrfeedback.qrbackend.repository.QRCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminDashboardController {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private QRCodeRepository qrRepo;

    @GetMapping("/{adminId}")
    public ResponseEntity<?> getDashboardStats(
            @PathVariable UUID adminId,
            @RequestParam(required = false)String sentiment,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate
            ){

        List<QRCode> qrCodes = qrRepo.findByAdminId(adminId);
        List<UUID> qrIds = qrCodes.stream().map(QRCode::getId).toList();

        List<Feedback> feedbacks = feedbackRepository.findAllByQrCodeIdInAndSentimentAndCreatedAtBetween(qrIds,sentiment,fromDate,toDate);

        DashboardStatsResponse response = DashboardUtil.aggregate(feedbacks);
        return ResponseEntity.ok(response);

    }
    @GetMapping("/admin-dashboard/{adminId}")
    public ResponseEntity<AdminDashboardResponse> getDashboardData(
            @PathVariable UUID adminId,
            @RequestParam(required = false) String sentiment,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate from,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate to
    ) {
        List<QRCode> qrCodes = qrRepo.findByAdminId(adminId);
        List<UUID> qrIds = qrCodes.stream().map(QRCode::getId).toList();

        List<Feedback> feedbacks = feedbackRepository.findFilteredFeedback(
                qrIds,
                sentiment,
                from != null ? from.atStartOfDay() : null,
                to != null ? to.atTime(LocalTime.MAX) : null
        );

        AdminDashboardResponse response = new AdminDashboardResponse(qrCodes, feedbacks);
        return ResponseEntity.ok(response);
    }

}
