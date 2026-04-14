package com.dhatvibs.modules.dto.vendorRegistration;

import com.dhatvibs.modules.entity.OtpType;
import lombok.Data;

import java.util.UUID;

@Data
public class ResendOtpRequest {
    private UUID vendorId;
    private OtpType otpType;
}