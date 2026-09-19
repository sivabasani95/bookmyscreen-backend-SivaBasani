package com.example.bookmyscreenbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Holds the email and OTP entered by the user for verification.
public class VerifyOtpRequest {

    // Makes sure the email is not empty.
    @NotBlank(message = "Email is required")

    // Makes sure the email has a valid format.
    @Email(message = "Please enter a valid email address")
    private String email;

    // Makes sure the OTP is provided.
    @NotBlank(message = "OTP is required")
    private String otp;

    // Empty constructor is needed when Spring reads JSON request data.
    public VerifyOtpRequest() {
    }

    // Returns the user's email address.
    public String getEmail() {
        return email;
    }

    // Sets the user's email address.
    public void setEmail(String email) {
        this.email = email;
    }

    // Returns the OTP entered by the user.
    public String getOtp() {
        return otp;
    }

    // Sets the OTP entered by the user.
    public void setOtp(String otp) {
        this.otp = otp;
    }
}