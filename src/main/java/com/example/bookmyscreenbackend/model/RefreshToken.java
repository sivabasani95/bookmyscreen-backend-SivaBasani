package com.example.bookmyscreenbackend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

// Stores a refresh token so a user can receive a new access token without logging in again.
@Entity
@Table(name = "refresh_tokens")
public class RefreshToken {

    // Creates the primary key for each refresh-token record.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Stores the JWT refresh token and prevents duplicate tokens.
    @Column(nullable = false, unique = true, length = 1000)
    private String token;

    // Connects this refresh token to the user who owns it.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Stores the date and time when the refresh token was created.
    @Column(nullable = false)
    private LocalDateTime createdAt;

    // JPA needs an empty constructor when loading data from MySQL.
    public RefreshToken() {
    }

    // Creates a refresh token with its token value and user.
    public RefreshToken(String token, User user) {
        this.token = token;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }

    // Returns the refresh-token ID.
    public Long getId() {
        return id;
    }

    // Returns the stored token.
    public String getToken() {
        return token;
    }

    // Updates the stored token.
    public void setToken(String token) {
        this.token = token;
    }

    // Returns the user who owns this token.
    public User getUser() {
        return user;
    }

    // Changes the user associated with this token.
    public void setUser(User user) {
        this.user = user;
    }

    // Returns when the refresh token was created.
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Updates the token creation time.
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Automatically sets the creation time before saving a new token.
    @PrePersist
    public void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }
}