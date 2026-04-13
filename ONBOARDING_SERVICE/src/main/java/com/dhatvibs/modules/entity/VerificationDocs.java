package com.dhatvibs.modules.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;
 
@Entity
@Table(name = "verification_docs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class VerificationDocs {
 
   @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "vendor_id", nullable = false, unique = true)
   private Vendor vendor;
 
   // FSSAI
   @Column(name = "fssai_license_number", length = 14)
   private String fssaiLicenseNumber;
 
   @Column(name = "fssai_expiry_date")
   private LocalDate fssaiExpiryDate;
 
   @Column(name = "fssai_certificate_url")
   private String fssaiCertificateUrl;
 
   // PAN
   @Column(name = "pan_number", length = 10)
   private String panNumber;
 
   @Column(name = "pan_card_url")
   private String panCardUrl;
 
   // GST (optional — Skip button visible in UI)
   @Column(name = "gst_number", length = 15)
   private String gstNumber;
 
   @Column(name = "gst_certificate_url")
   private String gstCertificateUrl;
 
   // Menu images (up to 3 as shown in UI)
   @Column(name = "menu_image_url_1")
   private String menuImageUrl1;
 
   @Column(name = "menu_image_url_2")
   private String menuImageUrl2;
 
   @Column(name = "menu_image_url_3")
   private String menuImageUrl3;
}  