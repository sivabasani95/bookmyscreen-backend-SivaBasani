package com.example.bookmyscreenbackend.service;

import com.example.bookmyscreenbackend.model.Theater;
import com.example.bookmyscreenbackend.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
// THEATER SERVICE
// Handles theater logic and talks to TheaterRepository.

@Service
public class TheaterService {

    private final TheaterRepository theaterRepository;

    // Connect service with repository.
    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    // CREATE - save a new theater.
    public Theater CreateTheater(Theater theater) {
        return theaterRepository.save(theater);
    }

    // READ ALL - Get all theaters.
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }

    // READ ONE - Find theater by ID.
    public Theater getTheaterById(Long id) {
        return theaterRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Theater not found"));
    }

    // FILTER - Find theaters by state.
    public List<Theater> getTheatersByState(String state) {
        return theaterRepository.findByStateContainingIgnoreCase(state);
    }


}
