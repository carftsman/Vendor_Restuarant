package com.dhatvibs.modules.dto.vendorRegistration;

import lombok.Data;

@Data
public class VerifyEmailOtpRequest {
    private String email;
    private String otpCode;
}