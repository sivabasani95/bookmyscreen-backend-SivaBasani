package com.example.bookmyscreenbackend.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Represents one movie screening at a theater.
@Entity
@Table(name = "shows")
public class Show {

    // Unique ID for every show.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Movie playing in this show.
    // Many shows can belong to one movie.
    @ManyToOne
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    // Theater where this show is playing.
    // Many shows can belong to one theater.
    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    // Location where the show is available.
    @Column(nullable = false)
    private String location;

    // Viewing format such as 2D, 3D, IMAX, or PVR PXL.
    // Helps users select the movie experience.
    @Column(nullable = false)
    private String format;

    // Audio technology used for the show.
    // Dolby Atmos is the default value.
    private String audioType = "Dolby Atmos";

    // Time when the movie starts.
    // LocalTime stores only the time of day.
    @Column(nullable = false)
    private LocalTime startTime;

    // Date when the movie is shown.
    @Column(nullable = false)
    private LocalDate date;

    // Stores ticket prices for different seat categories.
    // Example: NORMAL = 270, EXECUTIVE = 290, PREMIUM = 510.
    @ElementCollection
    @CollectionTable(
            name = "show_prices",
            joinColumns = @JoinColumn(name = "show_id")
    )
    @MapKeyColumn(name = "seat_type")
    @Column(name = "price")
    private Map<String, Double> priceMap = new HashMap<>();

    // Stores all seats belonging to this show.
    // Seat information is stored in the show_seats table.
    @OneToMany(
            mappedBy = "show",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<ShowSeat> seatLayout = new ArrayList<>();

    // Stores when the show was created.
    private LocalDateTime createdAt;

    // Stores when the show was last updated.
    private LocalDateTime updatedAt;

    // JPA requires an empty constructor.
    public Show() {
    }

    // Automatically sets timestamps when a show is created.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Automatically updates the timestamp when show data changes.
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAudioType() {
        return audioType;
    }

    public void setAudioType(String audioType) {
        this.audioType = audioType;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
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

    public List<ShowSeat> getSeatLayout() {
        return seatLayout;
    }

    public void setSeatLayout(List<ShowSeat> seatLayout) {
        this.seatLayout = seatLayout;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}