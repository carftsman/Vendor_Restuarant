package com.dhatvibs.modules.entity;

import com.dhatvibs.modules.entity.RestaurantType;
import com.dhatvibs.modules.entity.VendorStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
 
import java.time.LocalDateTime;
import java.util.UUID;
 
@Entity
@Table(name = "vendor")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Vendor {
 
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @Column(name = "full_name", nullable = false)
   private String fullName;
 
   @Column(name = "phone_number", unique = true, nullable = false, length = 15)
   private String phoneNumber;
 
   @Column(name = "phone_verified", nullable = false)
   private boolean phoneVerified = false;
 
   @Column(name = "email", unique = true, nullable = false)
   private String email;
 
   @Column(name = "email_verified", nullable = false)
   private boolean emailVerified = false;
 
   @Column(name = "password_hash")
   private String passwordHash;
 
   @Column(name = "profile_photo_url")
   private String profilePhotoUrl;
 
   @Enumerated(EnumType.STRING)
   @Column(name = "restaurant_type")
   private RestaurantType restaurantType;
 
   @Enumerated(EnumType.STRING)
   @Column(name = "status", nullable = false)
   private VendorStatus status = VendorStatus.PENDING_VERIFICATION;
 
   @CreationTimestamp
   @Column(name = "created_at", updatable = false)
   private LocalDateTime createdAt;
 
   @UpdateTimestamp
   @Column(name = "updated_at")
   private LocalDateTime updatedAt;
 
   // Relationships
   @OneToOne(mappedBy = "vendor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private VendorAuth vendorAuth;
 
   @OneToOne(mappedBy = "vendor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private OnboardingProgress onboardingProgress;
 
   @OneToOne(mappedBy = "vendor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private RestaurantInfo restaurantInfo;
 
   @OneToOne(mappedBy = "vendor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private VerificationDocs verificationDocs;
 
   @OneToOne(mappedBy = "vendor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
   private BankDetails bankDetails;
   
   @PrePersist
   public void setDefaultStatus() {
       if (this.status == null) {
           this.status = VendorStatus.PENDING_VERIFICATION;
       }
   }
}  