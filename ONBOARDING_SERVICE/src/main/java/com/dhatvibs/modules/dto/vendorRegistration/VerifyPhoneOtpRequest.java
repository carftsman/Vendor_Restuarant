package com.dhatvibs.modules.dto.vendorRegistration;

import lombok.Data;

@Data
public class VerifyPhoneOtpRequest {
    private String phonenumber;
    private String otpCode;
}