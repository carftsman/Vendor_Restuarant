package com.dhatvibs.modules.dto.vendorRegistration;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class CreateAccountRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private String confirmPassword;
    private MultipartFile profilePhoto;
}