package com.example.bookmyscreenbackend.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.Map;

// Represents one movie show in a theater.
@Entity
@Table(name = "shows")
public class Show {
    // Unique ID for each show.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

// Movie playing in this show
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    // Theater where this show is playing.
    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater;

    // Location of the show .
    @Column(nullable = false)
    private String location;

    //Example: 2D, 3D, IMAX, PVR, PXL.
    @Column(nullable = false)
    private LocalDate date;

    // Stores ticket price for different seat types.
    @ElementCollection
    @CollectionTable(
            name = "show_price_map",
            joinColumns = @JoinColumn(name = "show_id")
    )
    @MapKeyColumn(name = "seat_type")
    @Column(name = "price")
    private Map<String, Double> priceMap = new HashMap<>();

    // Automatically stores when show was created.
    private LocalDateTime createdAt;

    // Automatically stores when show was updated.
    private LocalDateTime updatedAt;

    // JPA requires an empty constructor.
    public Show() {

    }

    // Set timestamps when show is first saved.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Update timestamp when show changes.
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Map<String, Double> getPriceMap() {
        return priceMap;
    }

    public void setPriceMap(Map<String, Double> priceMap) {
        this.priceMap = priceMap;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}