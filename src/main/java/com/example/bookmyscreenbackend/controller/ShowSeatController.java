package com.example.bookmyscreenbackend.controller;

import com.example.bookmyscreenbackend.model.ShowSeat;
import com.example.bookmyscreenbackend.service.ShowSeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Handles HTTP requests related to seats for a movie show.
@RestController
@RequestMapping("/api/shows")
@CrossOrigin(origins = {
        "http://localhost:5173",
        "http://localhost:5174"
})
public class ShowSeatController {

    // Service used to get show seat information.
    private final ShowSeatService showSeatService;

    // Constructor injection provides the required service.
    public ShowSeatController(
            ShowSeatService showSeatService) {

        this.showSeatService = showSeatService;
    }


    // Gets all seats belonging to one show.
    @GetMapping("/{showId}/seats")
    public ResponseEntity<List<ShowSeat>> getSeatsByShow(
            @PathVariable Long showId) {

        // Gets all seats for the selected show.
        List<ShowSeat> seats =
                showSeatService.getSeatsByShowId(showId);

        // Returns the seats to the React frontend.
        return ResponseEntity.ok(seats);
    }
}