package com.example.bookmyscreenbackend.controller;

import com.example.bookmyscreenbackend.dto.ShowRequest;
import com.example.bookmyscreenbackend.model.SeatStatus;
import com.example.bookmyscreenbackend.model.Show;
import com.example.bookmyscreenbackend.model.ShowSeat;
import com.example.bookmyscreenbackend.service.ShowService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

// SHOW CONTROLLER
//Receives show API requests and sends them to ShowService.
@RestController
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    // Connect controller with ShowService.
    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    //CRUD- oparations
    // CREATE - Add a new show.
    @PostMapping
    public ResponseEntity<Show> createShow(
            @Valid @RequestBody ShowRequest request) {

        Show savedShow = showService.createShow(request);

        return  ResponseEntity
                .status(HttpStatus.CREATED)
                .body(savedShow);


    }

    // SEARCH - GET show by movie, date, and location.
    // EXAMPLE: /api/shows?movieId=2&date=2026-09-10&location=Kolkata
    @GetMapping
    public List<Show> getShows(
            @RequestParam Long movieId,
            @RequestParam(required = false) LocalDate date,
            @RequestParam String location) {

        return showService.getShowsByMovieDateLocation(
                movieId,
                date,
                location
        );
    }

    // READ ONE - Get one show by ID.
    @GetMapping("/{id}")
    public Show getShowById(@PathVariable Long id) {
        return showService.getShowById(id);
    }

    // UPDATE SEAT - Change seat status.
    // Example: /api/shows/1/seats/A/2?status=BOOKED
    @PutMapping("/{showId}/seats/{row}/{seatNumber}")
    public ShowSeat updateSeatStatus(
            @PathVariable Long showId,
            @PathVariable String row,
            @PathVariable Integer seatNumber,
            @RequestParam SeatStatus status) {

        return showService.updateSeatStatus(
                showId,
                row,
                seatNumber,
                status
        );
    }

}
