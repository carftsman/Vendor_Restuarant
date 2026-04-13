package com.dhatvibs.modules.entity;

import com.dhatvibs.modules.entity.OnboardingStage;
import com.dhatvibs.modules.entity.ReviewStatus;
import jakarta.persistence.*;
import lombok.*;
 
import java.time.LocalDateTime;
import java.util.UUID;
 
@Entity
@Table(name = "onboarding_progress")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class OnboardingProgress {
 
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "vendor_id", nullable = false, unique = true)
   private Vendor vendor;
 
   /**
    * The stage the vendor is currently at.
    * Use this to redirect the vendor to the correct screen on app open.
    */
   @Enumerated(EnumType.STRING)
   @Column(name = "current_stage", nullable = false)
   private OnboardingStage currentStage = OnboardingStage.NOT_STARTED;
 
   // Individual step completion flags
   @Column(name = "restaurant_type_done", nullable = false)
   private boolean restaurantTypeDone = false;
 
   @Column(name = "restaurant_info_done", nullable = false)
   private boolean restaurantInfoDone = false;
 
   @Column(name = "address_done", nullable = false)
   private boolean addressDone = false;
 
   @Column(name = "delivery_area_done", nullable = false)
   private boolean deliveryAreaDone = false;
 
   @Column(name = "cuisine_done", nullable = false)
   private boolean cuisineDone = false;
 
   @Column(name = "hours_done", nullable = false)
   private boolean hoursDone = false;
 
   @Column(name = "photos_done", nullable = false)
   private boolean photosDone = false;
 
   @Column(name = "menu_done", nullable = false)
   private boolean menuDone = false;
 
   @Column(name = "fssai_done", nullable = false)
   private boolean fssaiDone = false;
 
   @Column(name = "pan_done", nullable = false)
   private boolean panDone = false;
 
   @Column(name = "gst_done", nullable = false)
   private boolean gstDone = false;
 
   @Column(name = "bank_done", nullable = false)
   private boolean bankDone = false;
 
   @Column(name = "upi_done", nullable = false)
   private boolean upiDone = false;
 
   @Column(name = "submitted", nullable = false)
   private boolean submitted = false;
 
   @Enumerated(EnumType.STRING)
   @Column(name = "review_status", nullable = false)
   private ReviewStatus reviewStatus = ReviewStatus.NOT_SUBMITTED;
 
   @Column(name = "rejection_reason", columnDefinition = "TEXT")
   private String rejectionReason;
 
   @Column(name = "submitted_at")
   private LocalDateTime submittedAt;
 
   @Column(name = "reviewed_at")
   private LocalDateTime reviewedAt;
}
