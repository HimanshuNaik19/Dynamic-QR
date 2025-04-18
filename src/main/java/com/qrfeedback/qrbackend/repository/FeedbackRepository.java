package com.qrfeedback.qrbackend.repository;

import com.qrfeedback.qrbackend.DTO.SentimentTrendResponse;
import com.qrfeedback.qrbackend.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, UUID>, JpaSpecificationExecutor<Feedback> {
    List<Feedback> findByQrCodeId(UUID qrId);
    List<Feedback> findBySentiment(String sentiment);
    List<Feedback> findAllByQrCodeIdInAndCreatedAtBetween(List<UUID> qrIds, LocalDateTime from, LocalDateTime to);
//    List<Feedback> findAllByQrCodeIdInAndFilters(List<UUID> qrIds, String sentiment, LocalDate fromDate, LocalDate toDate);
//    List<Feedback> findFilteredFeedback(List<UUID> qrIds, String sentiment, LocalDateTime fromDate, LocalDateTime toDate);

    @Query("SELECT f FROM Feedback f WHERE " +
            "(:formIds IS NULL OR f.form.id IN :formIds) AND " +
            "(:sentiment IS NULL OR f.sentiment = :sentiment) AND " +
            "(:startDate IS NULL OR f.createdAt >= :startDate) AND " +
            "(:endDate IS NULL OR f.createdAt <= :endDate)")
    List<Feedback> findFilteredFeedback(
            @Param("formIds") List<UUID> formIds,
            @Param("sentiment") String sentiment,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );

    @Query("SELECT f FROM Feedback f WHERE " +
            "(:qrId IS NULL OR f.qrCode.id = :qrId) AND " +
            "(:startDate IS NULL OR f.createdAt >= :startDate) AND " +
            "(:endDate IS NULL OR f.createdAt <= :endDate) AND " +
            "(COALESCE(:sentiments, NULL) IS NULL OR f.sentiment IN (:sentiments)) " +
            "ORDER BY f.createdAt DESC")
    List<Feedback> filterFeedback(
            @Param("qrId") UUID qrId,
            @Param("sentiments") List<String> sentiments,
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.qrCode.id = :qrId")
    long countByQrCodeId(@Param("qrId") UUID qrId);

    @Query("SELECT COUNT(f) FROM Feedback f WHERE f.qrCode.id = :qrId AND f.sentiment = 'positive'")
    long countPositiveByQrCodeId(@Param("qrId") UUID qrId);

    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.qrCode.id = :qrId")
    Double averageRatingByQrCodeId(@Param("qrId") UUID qrId);

    @Query("""
    SELECT new com.qrfeedback.qrbackend.dto.SentimentTrendResponse(
        DATE(f.createdAt),
        SUM(CASE WHEN f.sentiment = 'positive' THEN 1 ELSE 0 END),
        SUM(CASE WHEN f.sentiment = 'neutral' THEN 1 ELSE 0 END),
        SUM(CASE WHEN f.sentiment = 'negative' THEN 1 ELSE 0 END)
    )
    FROM Feedback f
    WHERE f.qrCode.id = :qrId
    GROUP BY DATE(f.createdAt)
    ORDER BY DATE(f.createdAt)
""")
    List<SentimentTrendResponse> getSentimentTrends(@Param("qrId") UUID qrId);

    @Query("SELECT " +
            "SUM(CASE WHEN f.sentiment = 'positive' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN f.sentiment = 'neutral' THEN 1 ELSE 0 END), " +
            "SUM(CASE WHEN f.sentiment = 'negative' THEN 1 ELSE 0 END) " +
            "FROM Feedback f " +
            "WHERE f.qrCode.id = :qrId")
    Object[] getSentimentDistribution(@Param("qrId") UUID qrId);

    @Query("SELECT AVG(f.rating) FROM Feedback f WHERE f.qrCode.id = :qrId")
    Double findAverageRatingByQrCodeId(@Param("qrId") UUID qrId);

    List<Feedback> findAllByQrCodeIdInAndSentimentAndCreatedAtBetween(
            List<UUID> qrCodeIds,
            String sentiment,
            LocalDate fromDate,
            LocalDate toDate
    );
}
