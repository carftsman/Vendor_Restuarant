package com.dhatvibs.modules.controller.vendorRegistration;

import com.dhatvibs.modules.dto.vendorRegistration.*;
import com.dhatvibs.modules.service.vendorRegistration.RegistrationService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/registration")
@RequiredArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping(value = "/account", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Object createAccount(@ModelAttribute CreateAccountRequest request) {
        return registrationService.createAccount(request);
    }

    @PostMapping("/verify-phone")
    public ApiResponse<?> verifyPhone(@RequestBody VerifyPhoneOtpRequest request) {
        return registrationService.verifyPhoneOtp(request);
    }

    @PostMapping("/verify-email")
    public ApiResponse<?> verifyEmail(@RequestBody VerifyEmailOtpRequest request) {
        return registrationService.verifyEmailOtp(request);
    }

    @PostMapping("/resend-otp")
    public ApiResponse<?> resendOtp(@RequestBody ResendOtpRequest request) {
        return registrationService.resendOtp(request);
    }
}