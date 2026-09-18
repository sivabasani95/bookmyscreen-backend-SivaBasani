package com.example.bookmyscreenbackend.repository;

import com.example.bookmyscreenbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Finds one user using their email address.
    Optional<User> findByEmail(String email);

    // Checks whether an email already exists in the database.
    boolean existsByEmail(String email);
}
