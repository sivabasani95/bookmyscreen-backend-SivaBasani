package com.example.bookmyscreenbackend.repository;

import com.example.bookmyscreenbackend.model.ShowSeat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// Handles database operations for show seats.
public interface ShowSeatRepository extends JpaRepository<ShowSeat, Long> {

    // Finds one specific seat in a specific show.
    Optional<ShowSeat> findByShowIdAndRowIgnoreCaseAndNumber(
            Long showId,
            String row,
            Integer number
    );
}