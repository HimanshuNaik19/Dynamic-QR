package com.qrfeedback.qrbackend.repository;

import com.qrfeedback.qrbackend.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, UUID> {
    List<Feedback> findByQrCodeId(UUID qrId);
    List<Feedback> findBySentiment(String sentiment);
    List<Feedback> findAllByQrCodeIdInAndCreatedAtBetween(List<UUID> qrIds, LocalDateTime from, LocalDateTime to);
    List<Feedback> findAllByQrCodeIdInAndFilters(List<UUID> qrIds, String sentiment, LocalDate fromDate, LocalDate toDate);
}
