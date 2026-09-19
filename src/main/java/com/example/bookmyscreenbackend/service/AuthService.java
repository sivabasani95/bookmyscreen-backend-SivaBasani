package com.example.bookmyscreenbackend.service;

import org.springframework.stereotype.Service;

@Service
public class AuthService {

    // Gives this service access to OTP generation, email sending, and verification.
    private final OtpService otpService;

    // Injects OtpService into AuthService using constructor injection.
    public AuthService(OtpService otpService) {
        this.otpService = otpService;
    }

    // Generates and sends an OTP to the user's email address.
    public void sendOtp(String email) {
        otpService.sendOtp(email);
    }

    // Checks whether the OTP entered by the user is correct and not expired.
    public boolean verifyOtp(String email, String otp) {
        return otpService.verifyOtp(email, otp);
    }
}