package com.qrfeedback.qrbackend.repository;

import com.qrfeedback.qrbackend.model.QRCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface QRCodeRepository extends JpaRepository<QRCode, UUID> {
    List<QRCode> findByAdminId(UUID adminId);
}
