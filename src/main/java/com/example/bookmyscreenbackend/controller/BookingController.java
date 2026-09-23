package com.example.bookmyscreenbackend.controller;
import com.example.bookmyscreenbackend.dto.BookingRequest;
import com.example.bookmyscreenbackend.model.Booking;
import com.example.bookmyscreenbackend.service.BookingService;
import com.example.bookmyscreenbackend.service.TokenService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handles all HTTP requests related to movie bookings.
@RestController
@RequestMapping("/api/bookings")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class BookingController {

    // Service used for booking-related operations.
    private final BookingService bookingService;

    // Service used to verify JWT access tokens.
    private final TokenService tokenService;

    // Constructor injection provides the required services.
    public BookingController(
            BookingService bookingService,
            TokenService tokenService) {

        this.bookingService = bookingService;
        this.tokenService = tokenService;
    }


    // Creates a new booking for the currently logged-in user.
    @PostMapping
    public ResponseEntity<?> createBooking(
            @RequestHeader(
                    value = "Authorization",
                    required = false
            ) String authorizationHeader,
            @RequestBody BookingRequest bookingRequest) {

        // Checks whether the Authorization header contains a Bearer token.
        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Please sign in before booking.");
        }

        // Removes "Bearer " from the Authorization header.
        String token = authorizationHeader.substring(7);

        try {

            // Verifies the access token and reads its claims.
            Claims claims = tokenService.verifyToken(token);

            // Gets the logged-in user's email from the token.
            String email = claims.getSubject();

            // Creates the booking for the logged-in user.
            Booking booking =
                    bookingService.createBooking(
                            email,
                            bookingRequest
                    );

            // Returns HTTP 201 with the newly created booking.
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(booking);

        } catch (Exception exception) {

            // Returns an error message when booking creation fails.
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(exception.getMessage());
        }
    }


    // Returns all bookings belonging to the logged-in user.
    @GetMapping("/me")
    public ResponseEntity<?> getMyBookings(
            @RequestHeader(
                    value = "Authorization",
                    required = false
            ) String authorizationHeader) {

        // Checks whether the Authorization header contains a Bearer token.
        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Please sign in to view your bookings.");
        }

        // Removes "Bearer " from the Authorization header.
        String token = authorizationHeader.substring(7);

        try {

            // Verifies the access token and reads its claims.
            Claims claims = tokenService.verifyToken(token);

            // Gets the logged-in user's email from the token.
            String email = claims.getSubject();

            // Gets all bookings belonging to this user.
            List<Booking> bookings =
                    bookingService.getBookingsForUser(email);

            // Returns HTTP 200 with the user's bookings.
            return ResponseEntity.ok(bookings);

        } catch (Exception exception) {

            // Returns HTTP 401 when the access token is invalid or expired.
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid or expired access token.");
        }
    }
}