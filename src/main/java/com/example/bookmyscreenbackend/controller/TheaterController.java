package com.example.bookmyscreenbackend.controller;

import com.example.bookmyscreenbackend.model.Theater;
import com.example.bookmyscreenbackend.service.TheaterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
// THEATER CONTROLLER
// Receives theater API requests and sends them to TheaterService.

@RestController
@RequestMapping("/api/theaters")
public class TheaterController {

    private final TheaterService theaterService;

    // Connect controller with service.
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    // CREATE - Add a new theater.
    @PostMapping
    public ResponseEntity<Theater> createTheater(
            @RequestBody Theater theater) {

        Theater savedTheater = theaterService.createTheater(theater);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedTheater);
    }

    // READ ALL or FILTER BY STATE.
    // /api/theaters
    // /api/theaters?state=Missouri
    @GetMapping
    public List<Theater> getTheaters(
            @RequestParam(required = false) String state) {

        // If state is given, filter theaters by state.
        if (state != null && !state.isBlank()) {
            return theaterService.getTheatersByState(state);
        }

        // Otherwise return all theaters.
        return theaterService.getAllTheaters();
    }

    // READ ONE - Get theater by ID.
    @GetMapping("/{id}")
    public Theater getTheaterById(@PathVariable Long id) {
        return theaterService.getTheaterById(id);
    }

}
