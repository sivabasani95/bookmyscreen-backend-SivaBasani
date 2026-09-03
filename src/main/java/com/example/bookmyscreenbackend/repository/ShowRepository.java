package com.example.bookmyscreenbackend.repository;

import com.example.bookmyscreenbackend.model.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;

// Connects Show entity with MySQL.
public interface ShowRepository extends JpaRepository<Show, Long> {

    // Find shows by movie ,date , and location.
    List<Show> findByMovieIdAndDateAndLocationContainingIgnoreCaseOrderByStartTimeAsc(
            Long movieId,
            LocalDate date,
            String location
    );

    // Find shows by movie and location when date is not provided.

    List<Show> findByMovieIdAndLocationContainingIgnoreCaseOrderByStartTimeAsc(
            Long movieId,
            String location
    );


}
