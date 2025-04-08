package com.qrfeedback.qrbackend.controller;

import com.qrfeedback.qrbackend.model.AdminLink;
import com.qrfeedback.qrbackend.repository.AdminLinkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/admin")
public class AdminLinkController {

    @Autowired
    private AdminLinkRepository adminLinkRepository;

    @PostMapping("/generate")
    public ResponseEntity<AdminLink> generateAdminLink(){
        AdminLink adminLink = new AdminLink();
        adminLink.setId(UUID.randomUUID());
        adminLink.setCreatedAt(LocalDateTime.now());
        adminLinkRepository.save(adminLink);
        return ResponseEntity.ok(adminLink);
    }

}
