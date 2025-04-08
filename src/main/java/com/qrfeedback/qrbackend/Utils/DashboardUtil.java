package com.qrfeedback.qrbackend.Utils;

import com.qrfeedback.qrbackend.DTO.DashboardStatsResponse;
import com.qrfeedback.qrbackend.model.Feedback;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DashboardUtil {

    public static DashboardStatsResponse aggregate(List<Feedback> feedbacks) {
        DashboardStatsResponse res = new DashboardStatsResponse();

        res.setTotalFeedback(feedbacks.size());

        double avg = feedbacks.stream()
                .mapToInt(Feedback::getRating)
                .average().orElse(0.0);
        res.setAverageRating(avg);

        Map<String, Long> sentiments = feedbacks.stream()
                .collect(Collectors.groupingBy(Feedback::getSentiment, Collectors.counting()));
        res.setSentimentCounts(sentiments);

        Map<LocalDate, Double> dateWiseAvg = feedbacks.stream()
                .collect(Collectors.groupingBy(
                        f -> f.getCreatedAt().toLocalDate(),
                        Collectors.averagingInt(Feedback::getRating)
                ));

        List<DashboardStatsResponse.DailyStats> dailyStats = dateWiseAvg.entrySet().stream()
                .map(e -> {
                    DashboardStatsResponse.DailyStats ds = new DashboardStatsResponse.DailyStats();
                    ds.setDate(e.getKey());
                    ds.setAverageRating(e.getValue());
                    return ds;
                })
                .sorted(Comparator.comparing(DashboardStatsResponse.DailyStats::getDate))
                .toList();

        res.setDailyFeedbackStats(dailyStats);
        return res;
    }
}
