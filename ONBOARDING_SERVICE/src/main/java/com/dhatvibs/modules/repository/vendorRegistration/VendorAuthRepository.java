package com.dhatvibs.modules.repository.vendorRegistration;

import com.dhatvibs.modules.entity.VendorAuth;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VendorAuthRepository extends JpaRepository<VendorAuth, UUID> {
    Optional<VendorAuth> findByVendorId(UUID vendorId);
}