package com.example.bookmyscreenbackend.service;

import com.example.bookmyscreenbackend.model.RefreshToken;
import com.example.bookmyscreenbackend.model.User;
import com.example.bookmyscreenbackend.repository.RefreshTokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Optional;

// Handles creating, verifying, storing, and deleting JWT tokens.
@Service
public class TokenService {

    // Repository used to store and retrieve refresh tokens from MySQL.
    private final RefreshTokenRepository refreshTokenRepository;

    // Reads the JWT secret key from application.properties.
    @Value("${jwt.secret}")
    private String jwtSecret;

    // Reads the access-token expiration time from application.properties.
    @Value("${jwt.access-expiration}")
    private long accessExpiration;

    // Reads the refresh-token expiration time from application.properties.
    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    // Constructor injection gives this service access to the refresh-token repository.
    public TokenService(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    // Converts the JWT secret text into a secure signing key.
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                jwtSecret.getBytes(StandardCharsets.UTF_8)
        );
    }

    // Creates a short-lived access token for the logged-in user.
    public String generateAccessToken(User user) {

        return Jwts.builder()

                // Stores the user's email as the token subject.
                .subject(user.getEmail())

                // Stores the user's database ID inside the token.
                .claim("userId", user.getId())

                // Stores the user's role inside the token.
                .claim("role", user.getRole())

                // Records when the access token was created.
                .issuedAt(new Date())

                // Sets when the access token will expire.
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + accessExpiration
                        )
                )

                // Signs the token using our secret key.
                .signWith(getSigningKey())

                // Converts the JWT into a String.
                .compact();
    }

    // Creates a longer-lived refresh token for the logged-in user.
    public String generateRefreshToken(User user) {

        return Jwts.builder()

                // Stores the user's email as the token subject.
                .subject(user.getEmail())

                // Stores the user's database ID inside the token.
                .claim("userId", user.getId())

                // Records when the refresh token was created.
                .issuedAt(new Date())

                // Sets when the refresh token will expire.
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + refreshExpiration
                        )
                )

                // Signs the refresh token using our secret key.
                .signWith(getSigningKey())

                // Converts the JWT into a String.
                .compact();
    }

    // Saves a refresh token in the MySQL database.
    public RefreshToken storeRefreshToken(User user, String token) {

        // Create a refresh-token entity connected to the user.
        RefreshToken refreshToken =
                new RefreshToken(token, user);

        // Save the refresh token and return the saved record.
        return refreshTokenRepository.save(refreshToken);
    }

    // Finds a refresh token in MySQL using the token value.
    public Optional<RefreshToken> findRefreshToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    // Verifies a JWT and returns the information stored inside it.
    public Claims verifyToken(String token) {

        return Jwts.parser()

                // Uses the same secret key that signed the token.
                .verifyWith(getSigningKey())

                // Creates the JWT parser.
                .build()

                // Verifies the signature and parses the token.
                .parseSignedClaims(token)

                // Returns the claims stored inside the token.
                .getPayload();
    }

    // Deletes a refresh token when it is no longer needed.
    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }
}