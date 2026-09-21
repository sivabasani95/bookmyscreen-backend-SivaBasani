

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

    // Generates and sends an OTP to the user's email address.
    public void sendOtp(String email) {

        // Ask OtpService to generate, store, and email the 4-digit OTP.
        otpService.sendOtp(email);
    }

    // Verifies the OTP and returns authentication information for the user.
    public Map<String, Object> verifyOtp(String email, String otp) {

        // Check whether the entered OTP is correct and has not expired.
        boolean isValid = otpService.verifyOtp(email, otp);

        // Stop authentication when the OTP is incorrect or expired.
        if (!isValid) {
            return null;
        }

        // Find the user in MySQL using the verified email address.
        User user = userService.findOrCreateUser(email);

        // Generate a short-lived access token for the verified user.
        String accessToken =
                tokenService.generateAccessToken(user);

        // Generate a longer-lived refresh token for the verified user.
        String refreshToken =
                tokenService.generateRefreshToken(user);

        // Store the refresh token in MySQL so it can be used later.
        tokenService.storeRefreshToken(user, refreshToken);

        // Create the response that will be returned to the React frontend.
        Map<String, Object> response = new HashMap<>();

        // Tell the frontend that the OTP verification was successful.
        response.put("auth", true);

        // Return the user so React can check the activateUser value.
        response.put("user", user);

        // Return the access token to the React frontend.
        response.put("accessToken", accessToken);

        // Return the refresh token to the React frontend.
        response.put("refreshToken", refreshToken);

        // Return all authentication information to the controller.
        return response;
    }
}