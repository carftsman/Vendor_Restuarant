package com.dhatvibs.modules.repository.vendorRegistration;

import com.dhatvibs.modules.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface VendorRepository extends JpaRepository<Vendor, UUID> {
    Optional<Vendor> findByPhoneNumber(String phone);
    Optional<Vendor> findByEmail(String email);
}