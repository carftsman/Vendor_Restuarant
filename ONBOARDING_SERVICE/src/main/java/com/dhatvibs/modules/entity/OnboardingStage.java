package com.dhatvibs.modules.entity;

/** 

 * Tracks the exact stage at which a vendor paused or stopped 

 * during the onboarding process. Used to resume from the correct screen. 

 * 

 * Flow order (screens → stages): 

 * 

 * [AUTH] 

 *   Splash → Welcome → Login/OTP → OTP Verify 

 *   → Create Account → Phone OTP → Email OTP 

 * 

 * [REGISTRATION] 

 *   Restaurant Type → Restaurant Info → Address 

 *   → Delivery Area → Select Cuisines → Operating Hours 

 *   → Restaurant Photos → Upload Menu → FSSAI Verification 

 *   → PAN Verification → GST Verification → Bank Details 

 *   → UPI Setup → Review Application → Submitted 

 */ 

public enum OnboardingStage { 

    // ── Auth stages ──────────────────────────────────────────────────────────── 

    /** Vendor just installed, hasn't done anything yet */ 

    NOT_STARTED, 

    /** Vendor is on the Create Account form (name, phone, email, password) */ 

    ACCOUNT_CREATION, 

    /** Phone OTP sent during registration, waiting for verification */ 

    PHONE_OTP_PENDING, 

    /** Email OTP sent during registration, waiting for verification */ 

    EMAIL_OTP_PENDING, 

    /** Account fully created, phone & email verified */ 

    ACCOUNT_VERIFIED, 

    // ── Restaurant setup stages ──────────────────────────────────────────────── 

    /** Vendor is selecting restaurant type (Delivery / Dining / Both) */ 

    RESTAURANT_TYPE, 

    /** Vendor is filling in restaurant basic info */ 

    RESTAURANT_INFO, 

    /** Vendor is entering restaurant address */ 

    RESTAURANT_ADDRESS, 

    /** Vendor is setting delivery area on map and radius */ 

    DELIVERY_AREA, 

    /** Vendor is selecting cuisines */ 

    CUISINE_SELECTION, 

    /** Vendor is configuring operating hours per day */ 

    OPERATING_HOURS, 

    /** Vendor is uploading exterior, interior, kitchen, food photos */ 

    RESTAURANT_PHOTOS, 

    /** Vendor is uploading menu images */ 

    MENU_UPLOAD, 

    // ── Document & compliance stages ────────────────────────────────────────── 

    /** Vendor is entering FSSAI license number and uploading certificate */ 

    FSSAI_VERIFICATION, 

    /** Vendor is entering PAN number and uploading PAN card */ 

    PAN_VERIFICATION, 

    /** Vendor is entering GST number and uploading GST certificate */ 

    GST_VERIFICATION, 

    /** Vendor is entering bank account details and uploading cancelled cheque */ 

    BANK_DETAILS, 

    /** Vendor is entering/verifying UPI ID */ 

    UPI_SETUP, 

    // ── Review stages ────────────────────────────────────────────────────────── 

    /** Vendor is on the Review Application summary screen */ 

    REVIEW, 

    /** Vendor submitted application — awaiting admin decision */ 

    SUBMITTED, 

    /** Admin approved — vendor can access dashboard */ 

    APPROVED, 

    /** Admin rejected — vendor must fix and resubmit docs */ 

    REJECTED 

}   

