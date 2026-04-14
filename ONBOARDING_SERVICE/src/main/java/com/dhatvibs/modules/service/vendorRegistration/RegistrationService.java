package com.dhatvibs.modules.service.vendorRegistration;

import com.dhatvibs.modules.dto.vendorRegistration.*;

public interface RegistrationService {

    ApiResponse<?> createAccount(CreateAccountRequest request);

    ApiResponse<?> verifyPhoneOtp(VerifyPhoneOtpRequest request);

    ApiResponse<?> verifyEmailOtp(VerifyEmailOtpRequest request);

    ApiResponse<?> resendOtp(ResendOtpRequest request);
    
}