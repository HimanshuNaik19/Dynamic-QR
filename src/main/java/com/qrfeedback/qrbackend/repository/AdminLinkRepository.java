package com.qrfeedback.qrbackend.repository;

import com.qrfeedback.qrbackend.model.AdminLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface AdminLinkRepository extends JpaRepository<AdminLink, UUID> {
}
