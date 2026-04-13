package com.dhatvibs.modules.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
 
import java.time.LocalDateTime;
import java.util.UUID;
 
@Entity
@Table(name = "auth_token",
      indexes = {
          @Index(name = "idx_auth_token_vendor", columnList = "vendor_id"),
          @Index(name = "idx_refresh_token", columnList = "refresh_token")
      })
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class AuthToken {
 
   @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "vendor_id", nullable = false)
   private Vendor vendor;
 
   /**
    * Hashed JWT access token (store hash, not raw token — security best practice).
    * The raw JWT is signed and sent to the client; only the hash is stored for lookup.
    */
   @Column(name = "access_token", nullable = false, columnDefinition = "TEXT")
   private String accessToken;
 
   /**
    * Hashed refresh token stored in DB for revocation support.
    */
   @Column(name = "refresh_token", nullable = false, unique = true, columnDefinition = "TEXT")
   private String refreshToken;
 
   /** Always "Bearer" */
   @Column(name = "token_type", nullable = false, length = 20)
   private String tokenType = "Bearer";
 
   @Column(name = "access_expires_at", nullable = false)
   private LocalDateTime accessExpiresAt;
 
   @Column(name = "refresh_expires_at", nullable = false)
   private LocalDateTime refreshExpiresAt;
 
   /** e.g. "Android 14 / Samsung Galaxy S23" — helps detect suspicious logins */
   @Column(name = "device_info")
   private String deviceInfo;
 
   /** Set to true on logout or when a suspicious login is detected */
   @Column(name = "is_revoked", nullable = false)
   private boolean isRevoked = false;
 
   @CreationTimestamp
   @Column(name = "created_at", updatable = false)
   private LocalDateTime createdAt;
}  
