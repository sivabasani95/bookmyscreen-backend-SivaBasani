package com.example.bookmyscreenbackend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// This class is the controller for Movie-related API requests.
// It receives requests from the frontend and sends responses back.
@RestController
@RequestMapping("/api/movies")
public class MovieController {

    // GET request used to test if the Movie API is working.
    // URL: http://localhost:8080/api/movies
    @GetMapping
    public String getMovies() {
        return "Movies API is working";
    }

}
