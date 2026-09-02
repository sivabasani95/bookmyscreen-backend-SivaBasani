package com.example.bookmyscreenbackend.service;

import com.example.bookmyscreenbackend.dto.ShowRequest;
import com.example.bookmyscreenbackend.model.Movie;
import com.example.bookmyscreenbackend.model.SeatStatus;
import com.example.bookmyscreenbackend.model.Show;
import com.example.bookmyscreenbackend.model.ShowSeat;
import com.example.bookmyscreenbackend.model.Theater;
import com.example.bookmyscreenbackend.repository.MovieRepository;
import com.example.bookmyscreenbackend.repository.ShowRepository;
import com.example.bookmyscreenbackend.repository.ShowSeatRepository;
import com.example.bookmyscreenbackend.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

// SHOW SERVICE
// Handles show logic and talks to the repositories.
@Service
public class ShowService {

    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;
    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;

    // Connects the service with all required repositories.
    public ShowService(
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository,
            MovieRepository movieRepository,
            TheaterRepository theaterRepository) {

        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
    }

    // CREATE - Creates a show and automatically generates its seats.
    // Movie and theater are found using their IDs.
    public Show createShow(ShowRequest request) {

        Movie movie = movieRepository.findById(request.getMovieId())
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Theater theater = theaterRepository.findById(request.getTheaterId())
                .orElseThrow(() -> new RuntimeException("Theater not found"));

        Show show = new Show();

        show.setMovie(movie);
        show.setTheater(theater);
        show.setLocation(request.getLocation());
        show.setFormat(request.getFormat());

        // Use custom audio type if provided.
        if (request.getAudioType() != null &&
                !request.getAudioType().isBlank()) {
            show.setAudioType(request.getAudioType());
        }

        show.setStartTime(request.getStartTime());
        show.setDate(request.getDate());
        show.setPriceMap(request.getPriceMap());

        // Generate seats for the new show.
        List<ShowSeat> seats = generateSeatLayout(show);
        show.setSeatLayout(seats);

        return showRepository.save(show);
    }

    // SEARCH - Finds shows by movie, date, and location.
    // Results are ordered by show start time.
    public List<Show> getShowsByMovieDateLocation(
            Long movieId,
            LocalDate date,
            String location) {

        if (date != null) {
            return showRepository
                    .findByMovieIdAndDateAndLocationContainingIgnoreCaseOrderByStartTimeAsc(
                            movieId,
                            date,
                            location
                    );
        }

        return showRepository
                .findByMovieIdAndLocationContainingIgnoreCaseOrderByStartTimeAsc(
                        movieId,
                        location
                );
    }

    // READ ONE - Finds one show by ID.
    // Shows an error if the show does not exist.
    public Show getShowById(Long id) {
        return showRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Show not found"));
    }

    // UPDATE SEAT - Changes one seat's status.
    // Status can be AVAILABLE, BOOKED, or BLOCKED.
    public ShowSeat updateSeatStatus(
            Long showId,
            String row,
            Integer seatNumber,
            SeatStatus status) {

        ShowSeat seat = showSeatRepository
                .findByShowIdAndRowIgnoreCaseAndNumber(
                        showId,
                        row,
                        seatNumber
                )
                .orElseThrow(() -> new RuntimeException("Seat not found"));

        seat.setStatus(status);

        return showSeatRepository.save(seat);
    }

    // Creates the default seat layout for every new show.
    // Rows A-D have 20 seats and row E has 10 seats.
    private List<ShowSeat> generateSeatLayout(Show show) {

        List<ShowSeat> seats = new ArrayList<>();

        for (char row = 'A'; row <= 'D'; row++) {

            for (int number = 1; number <= 20; number++) {

                ShowSeat seat = new ShowSeat();

                seat.setRow(String.valueOf(row));
                seat.setNumber(number);
                seat.setStatus(SeatStatus.AVAILABLE);
                seat.setShow(show);

                seats.add(seat);
            }
        }

        for (int number = 1; number <= 10; number++) {

            ShowSeat seat = new ShowSeat();

            seat.setRow("E");
            seat.setNumber(number);
            seat.setStatus(SeatStatus.AVAILABLE);
            seat.setShow(show);

            seats.add(seat);
        }

        return seats;
    }
}