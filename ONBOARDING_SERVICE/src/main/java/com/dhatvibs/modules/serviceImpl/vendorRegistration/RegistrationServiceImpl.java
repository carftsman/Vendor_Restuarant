package com.dhatvibs.modules.serviceImpl.vendorRegistration;

import com.dhatvibs.modules.config.AzureBlobService;
import com.dhatvibs.modules.dto.vendorRegistration.*;
import com.dhatvibs.modules.entity.*;
import com.dhatvibs.modules.repository.vendorRegistration.VendorAuthRepository;
import com.dhatvibs.modules.repository.vendorRegistration.VendorRepository;
import com.dhatvibs.modules.repository.vendorRegistration.RestaurantInfoRepository;
import com.dhatvibs.modules.repository.vendorRegistration.RestaurantRepository;
import com.dhatvibs.modules.service.vendorRegistration.RegistrationService;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {

    private final VendorRepository vendorRepository;
    private final VendorAuthRepository vendorAuthRepository;
    private final RestaurantInfoRepository restaurantInfoRepository; 
    private final PasswordEncoder passwordEncoder;
    private final AzureBlobService azureBlobService;

    private static final String STATIC_OTP = "333333";

    private static final String UPLOAD_DIR =
            System.getProperty("user.dir") + File.separator + "uploads";

    @Override
    public ApiResponse<?> createAccount(CreateAccountRequest request) {

        String photoUrl = null;

        try {
            MultipartFile file = request.getProfilePhoto();

            if (file != null && !file.isEmpty()) {
                photoUrl = azureBlobService.uploadFile(file);
            }

        } catch (Exception e) {
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }

        Vendor vendor = Vendor.builder()
                .fullName(request.getFullName())
                .phoneNumber(request.getPhoneNumber())
                .email(request.getEmail())
                .passwordHash(passwordEncoder.encode(request.getPassword()))
                .status(VendorStatus.PENDING_VERIFICATION)
                .profilePhotoUrl(photoUrl)
                .build();

        vendorRepository.save(vendor);

        VendorAuth auth = VendorAuth.builder()
                .vendor(vendor)
                .otpCode(STATIC_OTP)
                .otpType(OtpType.PHONE_REGISTRATION)
                .otpExpiresAt(LocalDateTime.now().plusSeconds(30))
                .build();

        vendorAuthRepository.save(auth);

        return ApiResponse.builder()
                .success(true)
                .message("Account created. OTP sent.")
                .data(Map.of(
                        "vendorId", vendor.getId(),
                        "onboardingStage", OnboardingStage.PHONE_OTP_PENDING
                ))
                .build();
    }

    @Override
    public ApiResponse<?> verifyPhoneOtp(VerifyPhoneOtpRequest request) {

        Vendor vendor = vendorRepository.findByPhoneNumber(request.getPhonenumber())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        VendorAuth auth = vendorAuthRepository.findByVendorId(vendor.getId())
                .orElseThrow(() -> new RuntimeException("Auth not found"));

        if (!auth.getOtpCode().equals(request.getOtpCode())) {
            throw new RuntimeException("Invalid OTP");
        }

        vendor.setPhoneVerified(true);
        vendorRepository.save(vendor);

        auth.setOtpType(OtpType.EMAIL_REGISTRATION);
        auth.setOtpCode(STATIC_OTP);
        auth.setOtpExpiresAt(LocalDateTime.now().plusSeconds(30));
        vendorAuthRepository.save(auth);

        return ApiResponse.builder()
                .success(true)
                .message("Phone verified")
                .data(Map.of(
                        "onboardingStage", OnboardingStage.EMAIL_OTP_PENDING
                ))
                .build();
    }

    @Override
    public ApiResponse<?> verifyEmailOtp(VerifyEmailOtpRequest request) {

        // 1. Get Vendor
        Vendor vendor = vendorRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Vendor not found"));

        // 2. Get Auth
        VendorAuth auth = vendorAuthRepository.findByVendorId(vendor.getId())
                .orElseThrow(() -> new RuntimeException("Auth not found"));

        // 3. Validate OTP
        if (!auth.getOtpCode().equals(request.getOtpCode())) {
            throw new RuntimeException("Invalid OTP");
        }

        // 4. Mark Email Verified
        vendor.setEmailVerified(true);
        vendorRepository.save(vendor);

        // ✅ 5. CREATE RESTAURANT INFO (DB SAFE)
        RestaurantInfo restaurant = new RestaurantInfo();
        restaurant.setVendor(vendor);

        // ⚠️ MUST fill NOT NULL fields
        restaurant.setName("TEMP_NAME");
        restaurant.setPhoneNumber(vendor.getPhoneNumber());
        restaurant.setEmail(vendor.getEmail());

        restaurant = restaurantInfoRepository.save(restaurant); // ✅ FIXED

		
		/*
		 * // 6. SESSION String registrationSessionId = UUID.randomUUID().toString();
		 * System.out.println("REGISTRATION SESSION ID: " + registrationSessionId); int
		 * sessionExpiryMinutes = 120;
		 * 
		 * System.out.println("{"); System.out.println("  \"sessionId\": \"" +
		 * registrationSessionId + "\","); System.out.println("  \"vendorId\": \"" +
		 * vendor.getId() + "\","); System.out.println("  \"restaurantId\": \"" +
		 * restaurant.getId() + "\","); System.out.println("  \"expiresAt\": \"" +
		 * sessionExpiryMinutes + " minutes\""); System.out.println("}");
		 * 
		 * // 7. RESPONSE return ApiResponse.builder() .success(true)
		 * .message("Email verified. Account ready. Proceed to restaurant setup.")
		 * .data(Map.of( "vendorId", vendor.getId(), "restaurantId", restaurant.getId(),
		 * "emailVerified", true, "onboardingStage", OnboardingStage.ACCOUNT_VERIFIED,
		 * "registrationSessionId", registrationSessionId, "sessionExpiresInMinutes",
		 * sessionExpiryMinutes, "note",
		 * "Pass X-Registration-Session header for all subsequent steps" )) .build();
		 */
        
     // 6. SESSION
        String registrationSessionId = UUID.randomUUID().toString();
        int sessionExpiryMinutes = 120;

        // ✅ CREATE SESSION OBJECT (THIS IS WHAT YOU WANT)
        Map<String, Object> session = Map.of(
                "sessionId", registrationSessionId,
                "vendorId", vendor.getId(),
                "restaurantId", restaurant.getId(),
                "onboardingStage", OnboardingStage.ACCOUNT_VERIFIED,
                "expiresInMinutes", sessionExpiryMinutes
        ); 
        System.out.println("  \"sessionId\": \"" +registrationSessionId + "\","); 
        System.out.println("  \"vendorId\": \"" +vendor.getId() + "\",");
        System.out.println("  \"restaurantId\": \"" +restaurant.getId() + "\",");
        System.out.println("  \"onbaordingStage\": \"" +OnboardingStage.ACCOUNT_VERIFIED+ "\",");
        System.out.println("  \"expiresAt\": \"" +sessionExpiryMinutes + " minutes\"");

        // 7. RESPONSE
        return ApiResponse.builder()
        		
                .success(true)
                .message("Email verified. Account ready. Proceed to restaurant setup.")
                .data(Map.of(
                        "session", session,   // ✅ EVERYTHING INSIDE SESSION
                        "emailVerified", true,
                        "note", "Pass X-Registration-Session header for all subsequent steps"
                ))
                .build();
    }

    @Override
    public ApiResponse<?> resendOtp(ResendOtpRequest request) {

        VendorAuth auth = vendorAuthRepository.findByVendorId(request.getVendorId())
                .orElseThrow(() -> new RuntimeException("Auth not found"));

        auth.setOtpCode(STATIC_OTP);
        auth.setOtpExpiresAt(LocalDateTime.now().plusSeconds(30));

        vendorAuthRepository.save(auth);

        return ApiResponse.builder()
                .success(true)
                .message("OTP resent")
                .data(Map.of(
                        "otpExpiresInSeconds", 30
                ))
                .build();
    }
}