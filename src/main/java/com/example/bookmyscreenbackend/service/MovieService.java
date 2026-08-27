package com.example.bookmyscreenbackend.service;

import com.example.bookmyscreenbackend.model.Movie;
import com.example.bookmyscreenbackend.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;


// This class contains the business logic for Movie.
// It talks to MovieRepository to read and save movie data in MySQL.
@Service
public class MovieService {

    private final MovieRepository movieRepository;

    // Constructor injection:
    // Spring automatically gives us MovieRepository here.
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    // CREATE
    // Saves a new movie into the database.
    public Movie addMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // READ ALL
    // Gets all movies from the database.
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // READ ONE - Find movie by ID. Show error if movie is not found.
    public Movie getMovieById(Long id) {
        return movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    // UPDATE
    // Updates an existing movie.
    public Movie updateMovie(Long id, Movie updatedMovie) {
        // Find the movie by ID. If not found, show an error.
        Movie existingMovie = movieRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
        // Replace old movie information with new information.
        existingMovie.setTitle(updatedMovie.getTitle());
        existingMovie.setDescription(updatedMovie.getDescription());
        existingMovie.setDuration(updatedMovie.getDuration());
        existingMovie.setGenre(updatedMovie.getGenre());
        existingMovie.setLanguage(updatedMovie.getLanguage());
        existingMovie.setReleaseDate(updatedMovie.getReleaseDate());
        existingMovie.setPosterUrl(updatedMovie.getPosterUrl());
        existingMovie.setBannerUrl(updatedMovie.getBannerUrl());
        existingMovie.setTrailerUrl(updatedMovie.getTrailerUrl());
        existingMovie.setCertificate(updatedMovie.getCertificate());
        existingMovie.setRating(updatedMovie.getRating());
        existingMovie.setIsActive(updatedMovie.getIsActive());

        return movieRepository.save(existingMovie);
    }
    // DELETE - Check movie exists, then delete it.
    public void deleteMovie(Long id) {

        if (!movieRepository.existsById(id)) {
            throw new RuntimeException("Movie not found");
        }

        movieRepository.deleteById(id);
    }

// Filter - Get movies by language.
    public List<Movie> getMoviesByLanguage(String language) {
        return movieRepository.findByLanguageIgnoreCase(language);
    }
 // FILTER - Get only active movies.
    public List<Movie> getActiveMovies() {
        return movieRepository.findByIsActiveTrue();
    }

    // SEARCH - Search movies by title.
    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

}