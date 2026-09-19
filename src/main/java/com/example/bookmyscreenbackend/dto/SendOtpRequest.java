package com.example.bookmyscreenbackend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

// Holds the email address sent by the user when requesting an OTP.
public class SendOtpRequest {

    // Makes sure the email is not empty.
    @NotBlank(message = "Email is required")

    // Makes sure the value has a valid email format.
    @Email(message = "Please enter a valid email address")
    private String email;

    // Empty constructor is needed when Spring converts JSON into this object.
    public SendOtpRequest() {
    }

    // Returns the user's email address.
    public String getEmail() {
        return email;
    }

    // Sets the user's email address.
    public void setEmail(String email) {
        this.email = email;
    }
}