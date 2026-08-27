package com.example.bookmyscreenbackend.controller;

import com.example.bookmyscreenbackend.model.Movie;
import com.example.bookmyscreenbackend.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// MOVIE CONTROLLER
// Receives Movie API requests and sends them to MovieService.
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    private final MovieService movieService;

    // Connect controller with MovieService.
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    // CREATE - Add a new movie.
    @PostMapping
    public Movie addMovie(@RequestBody Movie movie) {
        return movieService.addMovie(movie);
    }

    // READ ALL - Get all movies.
    @GetMapping
    public List<Movie> getAllMovies() {
        return movieService.getAllMovies();
    }

    // READ ONE - Get one movie by ID.
    @GetMapping("/{id}")
    public Movie getMovieById(@PathVariable Long id) {
        return movieService.getMovieById(id);
    }

    // UPDATE - Update a movie by ID.
    @PutMapping("/{id}")
    public Movie updateMovie(
            @PathVariable Long id,
            @RequestBody Movie movie) {

        return movieService.updateMovie(id, movie);
    }

    // DELETE - Delete a movie by ID.
    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }


    // ================= FILTER AND SEARCH =================
// FILTER - Get movies by language.
// Example: /api/movies/language?language=English
    @GetMapping("/language")
    public List<Movie> getMoviesByLanguage(@RequestParam String language) {
        return movieService.getMoviesByLanguage(language);
    }

    // FILTER - Get only active movies.
// Example: /api/movies/active
    @GetMapping("/active")
    public List<Movie> getActiveMovies() {
        return movieService.getActiveMovies();
    }

    // SEARCH - Search movies by title.
// Example: /api/movies/search?title=F1
    @GetMapping("/search")
    public List<Movie> searchMoviesByTitle(@RequestParam String title) {
        return movieService.searchMoviesByTitle(title);
    }

}