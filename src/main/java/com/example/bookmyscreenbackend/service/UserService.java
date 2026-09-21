package com.example.bookmyscreenbackend.service;

import com.example.bookmyscreenbackend.model.User;
import com.example.bookmyscreenbackend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Service class that contains the business logic for users.
@Service
public class UserService {

    // Repository used to communicate with the users table in MySQL.
    private final UserRepository userRepository;

    // Constructor injection gives UserService access to UserRepository.
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Creates and saves a new user in the database.
    public User createUser(User user) {

        // Prevent creating multiple users with the same email.
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Save the new user in MySQL.
        return userRepository.save(user);
    }

    // Finds an existing user or creates a new temporary user.
    // This method is used after successful OTP verification.
    public User findOrCreateUser(String email) {

        // Check whether a user already exists with this email.
        return userRepository.findByEmail(email)

                // If the user does not exist, create a new user.
                .orElseGet(() -> {

                    // Create a new User object.
                    User newUser = new User();

                    // Save the verified email address.
                    newUser.setEmail(email);

                    // Give the new user the default role.
                    newUser.setRole("user");

                    // The account is not fully activated yet.
                    // The user still needs to enter name and phone.
                    newUser.setActivateUser(false);

                    // Save the temporary user in MySQL.
                    return userRepository.save(newUser);
                });
    }

    // Returns all users stored in the database.
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Finds one user using their database ID.
    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );
    }

    // Finds one user using their email address.
    public User getUserByEmail(String email) {

        return userRepository.findByEmail(email)
                .orElseThrow(
                        () -> new RuntimeException("User not found")
                );
    }

    // Completes the user's account setup.
    public User activateUser(Long id, String name, String phone) {

        // Find the existing user using their ID.
        User user = getUserById(id);

        // Save the name entered in Step 3.
        user.setName(name);

        // Save the phone number entered in Step 3.
        user.setPhone(phone);

        // Mark the account as activated.
        user.setActivateUser(true);

        // Save the updated user in MySQL.
        return userRepository.save(user);
    }

    // Deletes a user account using the user's ID.
    public void deleteUser(Long id) {

        // Check whether the user exists.
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }

        // Delete the user from MySQL.
        userRepository.deleteById(id);
    }
}