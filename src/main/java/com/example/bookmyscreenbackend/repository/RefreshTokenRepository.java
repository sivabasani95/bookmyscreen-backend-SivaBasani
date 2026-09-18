package com.example.bookmyscreenbackend.repository;

import com.example.bookmyscreenbackend.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Handles database operations for refresh tokens.
public interface RefreshTokenRepository
        extends JpaRepository<RefreshToken, Long> {

    // Finds a refresh token in the database using its token value.
    Optional<RefreshToken> findByToken(String token);

    // Deletes a refresh token when the user logs out.
    void deleteByToken(String token);
}