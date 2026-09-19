package com.example.bookmyscreenbackend.service;

import com.example.bookmyscreenbackend.model.User;
import com.example.bookmyscreenbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class that contains the business logic for users.
@Service
public class UserService {

    private final UserRepository userRepository;

    // Constructor injection gives UserService access to UserRepository.
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Creates and saves a new user in the database.
    public User createUser(User user) {

        // Prevents creating multiple users with the same email.
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        return userRepository.save(user);
    }

    // Returns all users stored in the database.
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Finds one user using their database ID.
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Finds one user using their email address.
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Activates a user account.
    public User activateUser(Long id) {

        // Find the existing user first.
        User user = getUserById(id);

        // Change the user's account status to active.
        user.setActivateUser(true);

        // Save the updated user in MySQL.
        return userRepository.save(user);
    }
    // Deletes a user account using the user's ID.
    public void deleteUser(Long id) {

        // Check that the user exists before trying to delete the account.
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        // Delete the user from the database.
        userRepository.deleteById(id);
    }

}