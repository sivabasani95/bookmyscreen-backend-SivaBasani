package com.example.bookmyscreenbackend.service;

import com.example.bookmyscreenbackend.model.User;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthService {

    // Handles OTP generation, email sending, and OTP verification.
    private final OtpService otpService;

    // Handles finding users stored in MySQL.
    private final UserService userService;

    // Handles access tokens and refresh tokens.
    private final TokenService tokenService;

    // Injects the services needed for authentication.
    public AuthService(
            OtpService otpService,
            UserService userService,
            TokenService tokenService) {

        this.otpService = otpService;
        this.userService = userService;
        this.tokenService = tokenService;
    }

    // Generates and sends an OTP to the user's email.
    public void sendOtp(String email) {
        otpService.sendOtp(email);
    }

    // Verifies the OTP and logs in the user.
    public Map<String, Object> verifyOtp(String email, String otp) {

        // Check whether the OTP is correct and has not expired.
        boolean isValid = otpService.verifyOtp(email, otp);

        // Stop authentication if the OTP is invalid.
        if (!isValid) {
            return null;
        }

        // Find the existing user using the verified email.
        User user = userService.getUserByEmail(email);

        // Activate the user after successful OTP verification.
        user = userService.activateUser(user.getId());

        // Generate a short-lived access token.
        String accessToken =
                tokenService.generateAccessToken(user);

        // Generate a longer-lived refresh token.
        String refreshToken =
                tokenService.generateRefreshToken(user);

        // Save the refresh token in MySQL.
        tokenService.storeRefreshToken(user, refreshToken);

        // Create the response returned after successful login.
        Map<String, Object> response = new HashMap<>();

        // Tell the frontend that authentication succeeded.
        response.put("auth", true);

        // Return the logged-in user's information.
        response.put("user", user);

        // Return the access token.
        response.put("accessToken", accessToken);

        // Return the refresh token.
        response.put("refreshToken", refreshToken);

        return response;
    }
}