package com.dhatvibs.modules.entity;

import com.dhatvibs.modules.entity.OtpType;
import jakarta.persistence.*;
import lombok.*;
 
import java.time.LocalDateTime;
import java.util.UUID;
 
@Entity
@Table(name = "vendor_auth")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VendorAuth {
 
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "vendor_id", nullable = false, unique = true)
   private Vendor vendor;
 
   // OTP fields (reused for both phone and email OTPs)
   @Column(name = "otp_code", length = 6)
   private String otpCode;
 
   @Enumerated(EnumType.STRING)
   @Column(name = "otp_type")
   private OtpType otpType;
 
   @Column(name = "otp_expires_at")
   private LocalDateTime otpExpiresAt;
 
   @Column(name = "otp_used", nullable = false)
   private boolean otpUsed = false;
 
   // Google OAuth fields
   @Column(name = "google_id", unique = true)
   private String googleId;
 
   @Column(name = "google_email")
   private String googleEmail;
 
   @Column(name = "last_login_at")
   private LocalDateTime lastLoginAt;
}  