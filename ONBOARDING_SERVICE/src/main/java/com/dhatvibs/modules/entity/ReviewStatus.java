package com.dhatvibs.modules.entity;

public enum ReviewStatus { 

    NOT_SUBMITTED,      // Vendor hasn't submitted yet 

     PENDING_REVIEW,     // Submitted, waiting for admin review 

    IN_REVIEW,          // Admin is actively reviewing 

    APPROVED,           // Application fully approved 

    REJECTED,           // Application rejected (reason stored separately) 

    RESUBMITTED         // Vendor corrected docs and resubmitted 

}  

