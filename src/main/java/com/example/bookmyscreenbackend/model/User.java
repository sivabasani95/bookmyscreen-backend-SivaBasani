package com.example.bookmyscreenbackend.model;
import jakarta.persistence.*;
import java.time.LocalDateTime;
// Represents a user stored in the MySQL database.
@Entity
@Table(name = "users")
public class User {

    // Primary key generated automatically by MySQL.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Stores the user's name after the user completes account setup.
    // Name can be null before the user completes Step 3.
    private String name;

    // Stores the user's email and prevents duplicate emails.
    @Column(nullable = false, unique = true)
    private String email;

    // Stores the user's role such as "user" or "admin".
    @Column(nullable = false)
    private String role = "user";

    // Stores the user's phone number.
    // Phone can be null before the user completes Step 3.
    private String phone;

    // Indicates whether the user's account has been activated.
    // New users start with false.
    @Column(nullable = false)
    private boolean activateUser = false;

    // Stores when the user was created.
    private LocalDateTime createdAt;

    // Stores when the user was last updated.
    private LocalDateTime updatedAt;

    // JPA requires a no-argument constructor.
    public User() {
    }

    // Sets timestamps automatically before inserting a new user.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Updates the modified timestamp automatically before an update.
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Returns the user's database ID.
    public Long getId() {
        return id;
    }

    // Updates the user's database ID.
    public void setId(Long id) {
        this.id = id;
    }

    // Returns the user's name.
    public String getName() {
        return name;
    }

    // Updates the user's name.
    public void setName(String name) {
        this.name = name;
    }

    // Returns the user's email.
    public String getEmail() {
        return email;
    }

    // Updates the user's email.
    public void setEmail(String email) {
        this.email = email;
    }

    // Returns the user's role.
    public String getRole() {
        return role;
    }

    // Updates the user's role.
    public void setRole(String role) {
        this.role = role;
    }

    // Returns the user's phone number.
    public String getPhone() {
        return phone;
    }

    // Updates the user's phone number.
    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Returns whether the user's account has been activated.
    public boolean isActivateUser() {
        return activateUser;
    }

    // Updates the user's activation status.
    public void setActivateUser(boolean activateUser) {
        this.activateUser = activateUser;
    }

    // Returns when the user was created.
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Updates when the user was created.
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    // Returns when the user was last updated.
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    // Updates when the user was last updated.
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}