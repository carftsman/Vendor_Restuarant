package com.dhatvibs.modules.entity;

public enum OtpType { 

    PHONE_REGISTRATION,   // OTP sent during account creation - phone 

    EMAIL_REGISTRATION,   // OTP sent during account creation - email 

    PHONE_LOGIN,          // OTP sent during login via phone 

    EMAIL_LOGIN,          // OTP sent during login via email 

    PHONE_CHANGE,         // OTP sent to verify new phone number 

    EMAIL_CHANGE          // OTP sent to verify new email address 

} 