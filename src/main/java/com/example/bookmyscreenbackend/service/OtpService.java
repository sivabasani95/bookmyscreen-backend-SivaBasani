package com.example.bookmyscreenbackend.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    // Sends OTP emails using the Gmail settings from application.properties.
    private final JavaMailSender mailSender;

    // Generates secure random numbers for the OTP.
    private final SecureRandom random = new SecureRandom();

    // Temporarily stores each user's OTP and expiration time using their email.
    private final Map<String, OtpData> otpStorage = new ConcurrentHashMap<>();

    // Gets the sender Gmail address from application.properties.
    @Value("${spring.mail.username}")
    private String fromEmail;

    // Constructor injection gives this service access to JavaMailSender.
    public OtpService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    // Generates a random 6-digit OTP.
    public String generateOtp() {
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    // Generates, stores, and sends a new OTP to the user's email.
    public void sendOtp(String email) {

        // Generate a new 6-digit OTP.
        String otp = generateOtp();

        // Set the OTP to expire after 2 minutes.
        LocalDateTime expiresAt = LocalDateTime.now().plusMinutes(2);

        // Store the OTP and expiration time temporarily in memory.
        otpStorage.put(email, new OtpData(otp, expiresAt));

        // Send the OTP to the user's email.
        sendOtpEmail(email, otp);
    }

    // Sends the generated OTP to the user's email address.
    private void sendOtpEmail(String email, String otp) {

        // Create a new email message.
        SimpleMailMessage message = new SimpleMailMessage();

        // Set the Gmail account that sends the email.
        message.setFrom(fromEmail);

        // Set the user's email as the receiver.
        message.setTo(email);

        // Set the email subject.
        message.setSubject("Your BookMyScreen Verification Code");

        // Set the email message containing the OTP.
        message.setText(
                "Welcome to BookMyScreen!\n\n" +
                        "Your verification code is: " + otp + "\n\n" +
                        "This OTP will expire in 2 minutes.\n\n" +
                        "If you did not request this code, you can ignore this email."
        );

        // Send the email through Gmail SMTP.
        mailSender.send(message);
    }

    // Verifies that the entered OTP is correct and has not expired.
    public boolean verifyOtp(String email, String enteredOtp) {

        // Get the OTP information stored for this email.
        OtpData otpData = otpStorage.get(email);

        // Return false if no OTP exists for this email.
        if (otpData == null) {
            return false;
        }

        // Check whether the OTP has expired.
        if (LocalDateTime.now().isAfter(otpData.expiresAt())) {

            // Remove the expired OTP from memory.
            otpStorage.remove(email);

            return false;
        }

        // Compare the OTP entered by the user with the stored OTP.
        boolean isValid = otpData.otp().equals(enteredOtp);

        // Remove the OTP after successful verification so it cannot be reused.
        if (isValid) {
            otpStorage.remove(email);
        }

        // Return true when the OTP is correct or false when it is incorrect.
        return isValid;
    }

    // Stores the OTP value together with its expiration time.
    private record OtpData(
            String otp,
            LocalDateTime expiresAt
    ) {
    }
}