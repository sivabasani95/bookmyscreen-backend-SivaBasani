package com.example.bookmyscreenbackend.controller;

import com.example.bookmyscreenbackend.model.User;
import com.example.bookmyscreenbackend.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handles all HTTP requests related to users.
@RestController

// Base URL for all user-related endpoints.
@RequestMapping("/api/users")

// Allows requests from your React frontend running on port 5173.
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    // Service object used to perform user-related business logic.
    private final UserService userService;

    // Constructor injection provides UserService to this controller.
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Creates a new user and saves the user in the database.
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User user) {

        // Send the received user data to the service layer.
        User createdUser = userService.createUser(user);

        // Return HTTP 201 Created with the newly created user.
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdUser);
    }

    // Returns all users stored in the database.
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {

        // Get all users from the service layer.
        List<User> users = userService.getAllUsers();

        // Return HTTP 200 OK with the list of users.
        return ResponseEntity.ok(users);
    }

    // Returns one user using the user's ID.
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {

        // Find the requested user through the service layer.
        User user = userService.getUserById(id);

        // Return HTTP 200 OK with the user.
        return ResponseEntity.ok(user);
    }

    // Activates an existing user account.
    @PutMapping("/activate/{id}")
    public ResponseEntity<User> activateUser(@PathVariable Long id) {

        // Change the user's activeUser value to true.
        User updatedUser = userService.activateUser(id);

        // Return HTTP 200 OK with the updated user.
        return ResponseEntity.ok(updatedUser);
    }

    // Deletes an existing user account using the user's ID.
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {

        // Ask the service layer to delete the user.
        userService.deleteUser(id);

        // Return a success message after deletion.
        return ResponseEntity.ok("User account deleted successfully");
    }
}