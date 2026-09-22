

package com.example.bookmyscreenbackend.config;

import com.example.bookmyscreenbackend.model.Movie;
import com.example.bookmyscreenbackend.model.SeatStatus;
import com.example.bookmyscreenbackend.model.Show;
import com.example.bookmyscreenbackend.model.ShowSeat;
import com.example.bookmyscreenbackend.model.Theater;
import com.example.bookmyscreenbackend.repository.MovieRepository;
import com.example.bookmyscreenbackend.repository.ShowRepository;
import com.example.bookmyscreenbackend.repository.ShowSeatRepository;
import com.example.bookmyscreenbackend.repository.TheaterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// SHOW DATA SEEDER
// Creates sample shows and makes sure every show has seats.
@Component
public class ShowDataSeeder implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;

    // Constructor injection gives this seeder access
    // to the required repositories.
    public ShowDataSeeder(
            MovieRepository movieRepository,
            TheaterRepository theaterRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository) {

        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }

    @Override
    public void run(String... args) {

        // =====================================================
        // EXISTING SHOWS
        // =====================================================
        // If shows already exist, do NOT create duplicate shows.
        // Instead, check whether each show has seats.
        if (showRepository.count() > 0) {

            List<Show> existingShows =
                    showRepository.findAll();

            for (Show show : existingShows) {

                // Get all seats belonging to this show.
                List<ShowSeat> existingSeats =
                        showSeatRepository.findByShowId(
                                show.getId()
                        );

                // If this show has no seats,
                // create the default 90-seat layout.
                if (existingSeats.isEmpty()) {

                    List<ShowSeat> seats =
                            generateSeatLayout(show);

                    show.setSeatLayout(seats);

                    // CascadeType.ALL in Show.java
                    // saves the seats automatically.
                    showRepository.save(show);
                }
            }

            // Existing shows have now been checked,
            // so do not create duplicate shows.
            return;
        }


        // =====================================================
        // NEW DATABASE / NO SHOWS EXIST
        // =====================================================

        // Get movies and theaters already stored in MySQL.
        List<Movie> movies =
                movieRepository.findAll();

        List<Theater> theaters =
                theaterRepository.findAll();

        // Shows cannot be created without
        // movies and theaters.
        if (movies.isEmpty() || theaters.isEmpty()) {

            System.out.println(
                    "Movies or theaters are missing. " +
                            "Show seeding skipped."
            );

            return;
        }


        // =====================================================
        // CREATE SHOW DATES
        // =====================================================

        // Create shows for today and the next six days.
        LocalDate today = LocalDate.now();

        List<LocalDate> showDates =
                new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            showDates.add(
                    today.plusDays(i)
            );
        }


        // =====================================================
        // SHOW TIMES
        // =====================================================

        List<LocalTime> timeSlots = List.of(
                LocalTime.of(9, 0),
                LocalTime.of(12, 30),
                LocalTime.of(16, 0),
                LocalTime.of(19, 30)
        );


        // =====================================================
        // MOVIE FORMATS
        // =====================================================

        List<String> formats = List.of(
                "2D",
                "3D",
                "IMAX",
                "PVR PXL"
        );


        // Stores all newly generated shows.
        List<Show> shows =
                new ArrayList<>();


        // =====================================================
        // SELECT MOVIES
        // =====================================================

        // Use all movies so every movie can
        // have show timings.
        List<Movie> selectedMovies = movies;


        // =====================================================
        // SELECT THEATERS
        // =====================================================

        // Use Missouri theaters because the frontend
        // searches shows using the state.
        List<Theater> selectedTheaters =
                theaters.stream()
                        .filter(theater ->
                                "Missouri".equalsIgnoreCase(
                                        theater.getState()
                                )
                        )
                        .limit(4)
                        .toList();

        if (selectedTheaters.isEmpty()) {

            System.out.println(
                    "No Missouri theaters found. " +
                            "Show seeding skipped."
            );

            return;
        }


        // =====================================================
        // CREATE SHOWS
        // =====================================================

        // Create shows for:
        //
        // every movie
        // every selected theater
        // seven dates
        // four time slots
        //
        // Every show also receives prices and seats.
        for (Movie movie : selectedMovies) {

            for (Theater theater : selectedTheaters) {

                for (LocalDate showDate : showDates) {

                    for (int i = 0;
                         i < timeSlots.size();
                         i++) {

                        Show show = new Show();

                        // Set movie.
                        show.setMovie(movie);

                        // Set theater.
                        show.setTheater(theater);

                        // Store theater state.
                        show.setLocation(
                                theater.getState()
                        );

                        // Set movie format.
                        show.setFormat(
                                formats.get(
                                        i % formats.size()
                                )
                        );

                        // Set audio type.
                        show.setAudioType(
                                "Dolby 7.1"
                        );

                        // Set show start time.
                        show.setStartTime(
                                timeSlots.get(i)
                        );

                        // Set show date.
                        show.setDate(
                                showDate
                        );

                        // Add ticket prices.
                        show.setPriceMap(
                                generatePriceMap()
                        );

                        // Create the default seat layout.
                        show.setSeatLayout(
                                generateSeatLayout(show)
                        );

                        // Add show to the list.
                        shows.add(show);
                    }
                }
            }
        }


        // =====================================================
        // SAVE SHOWS
        // =====================================================

        // CascadeType.ALL in Show.java also saves
        // every ShowSeat belonging to each show.
        showRepository.saveAll(shows);

        System.out.println(
                "Shows seeded successfully: "
                        + shows.size()
        );
    }


    // =========================================================
    // GENERATE TICKET PRICES
    // =========================================================

    // Creates ticket prices used by every show.
    private Map<String, Double> generatePriceMap() {

        Map<String, Double> prices =
                new HashMap<>();

        prices.put(
                "PREMIUM",
                15.00
        );

        prices.put(
                "EXECUTIVE",
                12.00
        );

        prices.put(
                "NORMAL",
                10.00
        );

        return prices;
    }


    // =========================================================
    // GENERATE SEAT LAYOUT
    // =========================================================

    // Creates 90 seats for one show.
    //
    // NORMAL:
    // A1 - A20
    //
    // EXECUTIVE:
    // B1 - B20
    // C1 - C20
    // D1 - D20
    //
    // PREMIUM:
    // E1 - E10
    //
    // Total = 90 seats.
    private List<ShowSeat> generateSeatLayout(
            Show show) {

        List<ShowSeat> seats =
                new ArrayList<>();


        // =====================================================
        // ROWS A - D
        // =====================================================

        // Create 20 seats in each row.
        for (char row = 'A';
             row <= 'D';
             row++) {

            for (int number = 1;
                 number <= 20;
                 number++) {

                ShowSeat seat =
                        new ShowSeat();

                // Example: A, B, C, D.
                seat.setRow(
                        String.valueOf(row)
                );

                // Example: 1 - 20.
                seat.setNumber(number);

                // New seats start as available.
                seat.setStatus(
                        SeatStatus.AVAILABLE
                );

                // Connect this seat to its show.
                seat.setShow(show);

                seats.add(seat);
            }
        }


        // =====================================================
        // ROW E
        // =====================================================

        // Premium row has 10 seats.
        for (int number = 1;
             number <= 10;
             number++) {

            ShowSeat seat =
                    new ShowSeat();

            seat.setRow("E");

            seat.setNumber(number);

            seat.setStatus(
                    SeatStatus.AVAILABLE
            );

            // Connect this seat to its show.
            seat.setShow(show);

            seats.add(seat);
        }


        return seats;
    }
}