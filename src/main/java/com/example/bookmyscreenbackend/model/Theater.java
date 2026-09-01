package com.example.bookmyscreenbackend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

// Represents the "theaters" table in MySQL.
@Entity
@Table(name = "theaters")
public class Theater {

    // Unique ID for each theater.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Theater name.
    @Column(nullable = false)
    private String name;

    // Theater location/address.
    @Column(nullable = false)
    private String location;

    // Theater logo URL.
    @Column(nullable = false)
    private String logo;

    // Theater city.
    @Column(nullable = false)
    private String city;

    // Theater state.
    @Column(nullable = false)
    private String state;

    // Stores when the theater was created.
    private LocalDateTime createdAt;

    // Stores when the theater was last updated.
    private LocalDateTime updatedAt;

    // JPA requires an empty constructor.
    public Theater() {
    }

    // Set timestamps when theater is first saved.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Update timestamp when theater changes.
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}