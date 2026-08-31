package com.example.bookmyscreenbackend.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

// DTO = Data Transfer Object.
// Receives and validates movie data from the frontend.
public class MovieRequest {

    // Title cannot be empty.
    @NotBlank(message = "Title is required")
    private String title;

    // Description cannot be empty.
    @NotBlank(message = "Description is required")
    private String description;

    // Duration must be provided.
    @NotNull(message = "Duration is required")
    private Integer duration;

    // Movie must have at least one genre.
    @NotEmpty(message = "At least one genre is required")
    private List<String> genre;

    // Movie language.
    private String language;

    // Movie release date.
    private LocalDate releaseDate;

    // Movie poster URL.
    private String posterUrl;

    // Movie banner URL.
    private String bannerUrl;

    // Movie trailer URL.
    private String trailerUrl;

    // Movie certificate.
    private String certificate;

    // Rating must be between 0 and 10.
    @DecimalMin(value = "0.0", message = "Rating cannot be less than 0")
    @DecimalMax(value = "10.0", message = "Rating cannot be more than 10")
    private Double rating;

    // Shows if movie is active.
    private Boolean isActive = true;

    // Getters and setters
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
}