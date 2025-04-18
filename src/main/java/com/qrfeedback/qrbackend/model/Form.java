package com.qrfeedback.qrbackend.model; // adjust this to match your package structure

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "form")
@Data
public class Form {

    @Id
    @GeneratedValue
    private UUID id;

    private String title;

    private String description;

    // Optional: You can link feedbacks back if needed
    @OneToMany(mappedBy = "form")
    private List<Feedback> feedbacks;

    // Add fields like createdBy, createdAt, etc., if you need

}

