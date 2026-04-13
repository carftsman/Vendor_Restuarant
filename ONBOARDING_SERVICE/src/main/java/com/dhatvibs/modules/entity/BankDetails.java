package com.dhatvibs.modules.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;
 
@Entity
@Table(name = "bank_details")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class BankDetails {
 
   @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private UUID id;
 
   @OneToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "vendor_id", nullable = false, unique = true)
   private Vendor vendor;
 
   @Column(name = "account_holder_name", nullable = false)
   private String accountHolderName;
 
   @Column(name = "bank_name")
   private String bankName;
 
   @Column(name = "account_number", nullable = false)
   private String accountNumber;
 
   @Column(name = "ifsc_code", nullable = false, length = 11)
   private String ifscCode;
 
   @Column(name = "branch_name")
   private String branchName;
 
   @Column(name = "cancelled_cheque_url")
   private String cancelledChequeUrl;
 
   // UPI Setup
   @Column(name = "upi_id", unique = true)
   private String upiId;
 
   @Column(name = "upi_verified", nullable = false)
   private boolean upiVerified = false;
}  
