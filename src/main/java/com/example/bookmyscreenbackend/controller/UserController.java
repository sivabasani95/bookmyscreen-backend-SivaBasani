

package com.example.bookmyscreenbackend.controller;

import com.example.bookmyscreenbackend.model.User;
import com.example.bookmyscreenbackend.service.TokenService;
import com.example.bookmyscreenbackend.service.UserService;
import io.jsonwebtoken.Claims;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

// Handles all HTTP requests related to users.
@RestController

// Base URL for all user endpoints.
@RequestMapping("/api/users")

// Allows requests from the React frontend.
// Vite may run on port 5173 or 5174.
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class UserController {

    // Service used for user-related operations.
    private final UserService userService;

    // Service used to verify JWT access tokens.
    private final TokenService tokenService;

    // Constructor injection gives this controller access
    // to UserService and TokenService.
    public UserController(
            UserService userService,
            TokenService tokenService) {

        this.userService = userService;
        this.tokenService = tokenService;
    }


    // ==========================================================
    // CREATE USER
    // ==========================================================

    // Creates a new user and saves the user in MySQL.
    @PostMapping
    public ResponseEntity<User> createUser(
            @RequestBody User user) {

        // Send the user data to the service layer.
        User createdUser = userService.createUser(user);

        // Return HTTP 201 Created with the new user.
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }


    // ==========================================================
    // GET ALL USERS
    // ==========================================================

    // Returns all users stored in MySQL.
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        // Get all users from the service layer.
        List<User> users = userService.getAllUsers();

        // Return HTTP 200 OK with the users.
        return ResponseEntity.ok(users);
    }


    // ==========================================================
    // GET CURRENT LOGGED-IN USER
    // ==========================================================

    // Returns the currently logged-in user.
    // React calls this endpoint using GET /api/users/me.
    @GetMapping("/me")
    public ResponseEntity<User> getCurrentUser(
            @RequestHeader(
                    value = "Authorization",
                    required = false
            ) String authorizationHeader) {

        // Check whether the Authorization header exists.
        if (authorizationHeader == null ||
                !authorizationHeader.startsWith("Bearer ")) {

            // Return HTTP 401 if there is no valid Bearer header.
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        // Remove "Bearer " from the beginning of the header.
        //
        // Example:
        // Bearer eyJhbGciOiJIUzI1NiJ9...
        //
        // becomes:
        // eyJhbGciOiJIUzI1NiJ9...
        String token = authorizationHeader.substring(7);

        try {

            // Verify the JWT access token.
            Claims claims = tokenService.verifyToken(token);

            // Get the user's email from the JWT.
            // The email was stored as the subject
            // when the token was created.
            String email = claims.getSubject();

            // Find the user in MySQL using the email.
            User user = userService.getUserByEmail(email);

            // Return HTTP 200 with the logged-in user.
            return ResponseEntity.ok(user);

        } catch (Exception exception) {

            // If the token is invalid or expired,
            // return HTTP 401 Unauthorized.
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }
    }


    // ==========================================================
    // GET USER BY ID
    // ==========================================================

    // Returns one user using the user's database ID.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(
            @PathVariable Long id) {

        // Find the user through the service layer.
        User user = userService.getUserById(id);

        // Return HTTP 200 with the user.
        return ResponseEntity.ok(user);
    }


    // ==========================================================
    // ACTIVATE USER
    // ==========================================================

    // Saves the user's name and phone number
    // and activates the user's account.
    @PutMapping("/activate/{id}")
    public ResponseEntity<User> activateUser(
            @PathVariable Long id,
            @RequestBody Map<String, String> userData) {

        // Get the name from the request body.
        String name = userData.get("name");

        // Get the phone number from the request body.
        String phone = userData.get("phone");

        // Update and activate the user.
        User updatedUser = userService.activateUser(
                id,
                name,
                phone
        );

        // Return HTTP 200 with the updated user.
        return ResponseEntity.ok(updatedUser);
    }


    // ==========================================================
    // DELETE USER
    // ==========================================================

    // Deletes a user using the user's database ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(
            @PathVariable Long id) {

        // Delete the user through the service layer.
        userService.deleteUser(id);

        // Return a success message.
        return ResponseEntity.ok(
                "User account deleted successfully"
        );
    }
}