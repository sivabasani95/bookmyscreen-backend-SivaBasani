package com.example.bookmyscreenbackend.controller;

import com.example.bookmyscreenbackend.dto.SendOtpRequest;
import com.example.bookmyscreenbackend.dto.VerifyOtpRequest;
import com.example.bookmyscreenbackend.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    // Gives this controller access to the authentication business logic.
    private final AuthService authService;

    // Injects AuthService into this controller using constructor injection.
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    // Receives the user's email and sends an OTP to that email address.
    @PostMapping("/send-otp")
    public ResponseEntity<Map<String, String>> sendOtp(
            @Valid @RequestBody SendOtpRequest request) {

        // Ask AuthService to generate and send an OTP to the user's email.
        authService.sendOtp(request.getEmail());

        // Return a success message after the OTP has been sent.
        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "OTP sent successfully."
                )
        );
    }

    // Receives the user's email and OTP and checks whether the OTP is valid.
    @PostMapping("/verify-otp")
    public ResponseEntity<Map<String, String>> verifyOtp(
            @Valid @RequestBody VerifyOtpRequest request) {

        // Ask AuthService to verify the OTP entered by the user.
        boolean isValid = authService.verifyOtp(
                request.getEmail(),
                request.getOtp()
        );

        // Return an error response if the OTP is incorrect or expired.
        if (!isValid) {
            return ResponseEntity
                    .badRequest()
                    .body(
                            Map.of(
                                    "message",
                                    "Invalid or expired OTP."
                            )
                    );
        }

        // Return a success response when the OTP is correct.
        return ResponseEntity.ok(
                Map.of(
                        "message",
                        "OTP verified successfully."
                )
        );
    }
}