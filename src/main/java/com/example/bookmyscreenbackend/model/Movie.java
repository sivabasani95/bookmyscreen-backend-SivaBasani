package com.example.bookmyscreenbackend.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Represents the "movies" table in MySQL.
@Entity
@Table(name = "movies")
public class Movie {

    // Unique ID for every movie.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Movie title - required.
    @Column(nullable = false)
    private String title;

    // Detailed information about the movie.
    @Column(length = 2000)
    private String description;

    // Movie duration in minutes.
    private Integer duration;

    // A movie can have multiple genres.
    // Example: Action, Drama, Thriller
    @ElementCollection
    @CollectionTable(
            name = "movie_genres",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    @Column(name = "genre")
    private List<String> genre = new ArrayList<>();

    // Example: English, Telugu, Hindi
    private String language;

    // Movie release date.
    private LocalDate releaseDate;

    // URL of the movie poster.
    private String posterUrl;

    // URL of the large banner image.
    private String bannerUrl;

    // URL of the movie trailer.
    private String trailerUrl;

    // Movie certificate.
    // Example: U, UA, A, PG-13
    private String certificate;

    // Movie rating.
    private Double rating;

    // Determines whether the movie is currently active.
    private Boolean isActive = true;

    // Date/time when movie was added.
    private LocalDateTime createdAt;

    // Date/time when movie was last updated.
    private LocalDateTime updatedAt;

    // JPA requires a no-argument constructor.
    public Movie() {
    }

    // Automatically set timestamps before inserting into MySQL.
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    // Automatically update updatedAt when movie changes.
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<String> getGenre() {
        return genre;
    }

    public void setGenre(List<String> genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl;
    }

    public String getTrailerUrl() {
        return trailerUrl;
    }

    public void setTrailerUrl(String trailerUrl) {
        this.trailerUrl = trailerUrl;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}