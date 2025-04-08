package com.qrfeedback.qrbackend.Utils;

public class SentimentUtil {
    public static String analyze(String comment) {
        String text = comment.toLowerCase();
        if (text.contains("bad") || text.contains("worst") || text.contains("terrible")) return "negative";
        if (text.contains("good") || text.contains("great") || text.contains("excellent")) return "positive";
        return "neutral";
    }
}
