package com.example.bookmyscreenbackend.config;

import com.example.bookmyscreenbackend.model.Movie;
import com.example.bookmyscreenbackend.model.SeatStatus;
import com.example.bookmyscreenbackend.model.Show;
import com.example.bookmyscreenbackend.model.ShowSeat;
import com.example.bookmyscreenbackend.model.Theater;
import com.example.bookmyscreenbackend.repository.MovieRepository;
import com.example.bookmyscreenbackend.repository.ShowRepository;
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
// Creates sample shows after movies and theaters already exist in MySQL.
@Component
public class ShowDataSeeder implements CommandLineRunner {

    private final MovieRepository movieRepository;
    private final TheaterRepository theaterRepository;
    private final ShowRepository showRepository;

    // Constructor injection gives this seeder access to the required repositories.
    // Shows depend on existing movies and theaters.
    public ShowDataSeeder(
            MovieRepository movieRepository,
            TheaterRepository theaterRepository,
            ShowRepository showRepository) {

        this.movieRepository = movieRepository;
        this.theaterRepository = theaterRepository;
        this.showRepository = showRepository;
    }

    @Override
    public void run(String... args) {

        // Prevent duplicate shows when the application restarts.
        // If shows already exist, the seeder stops here.
        if (showRepository.count() > 0) {
            return;
        }

        // Get movies and theaters that were already seeded.
        // Shows cannot be created without both records.
        List<Movie> movies = movieRepository.findAll();
        List<Theater> theaters = theaterRepository.findAll();

        if (movies.isEmpty() || theaters.isEmpty()) {
            System.out.println("Movies or theaters are missing. Show seeding skipped.");
            return;
        }

        // Create shows for today and tomorrow.
        // This keeps the sample booking dates current.
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);

        // Different show times used throughout the day.
        List<LocalTime> timeSlots = List.of(
                LocalTime.of(9, 0),
                LocalTime.of(12, 30),
                LocalTime.of(16, 0),
                LocalTime.of(19, 30)
        );

        // Different viewing formats available for shows.
        List<String> formats = List.of(
                "2D",
                "3D",
                "IMAX",
                "PVR PXL"
        );

        List<Show> shows = new ArrayList<>();

        // Use a few movies and Missouri theaters for sample booking data.
        // This keeps the seeder small and easy to test.
        List<Movie> selectedMovies = movies.stream()
                .limit(3)
                .toList();

        List<Theater> selectedTheaters = theaters.stream()
                .filter(theater -> "Missouri".equalsIgnoreCase(theater.getState()))
                .limit(4)
                .toList();

        if (selectedTheaters.isEmpty()) {
            System.out.println("No Missouri theaters found. Show seeding skipped.");
            return;
        }

        // Create shows for selected movies, theaters, dates, and time slots.
        // Each show also receives prices and a generated seat layout.
        for (Movie movie : selectedMovies) {

            for (Theater theater : selectedTheaters) {

                for (LocalDate showDate : List.of(today, tomorrow)) {

                    for (int i = 0; i < timeSlots.size(); i++) {

                        Show show = new Show();

                        show.setMovie(movie);
                        show.setTheater(theater);
                        show.setLocation(theater.getState());
                        show.setFormat(formats.get(i % formats.size()));
                        show.setAudioType("Dolby 7.1");
                        show.setStartTime(timeSlots.get(i));
                        show.setDate(showDate);

                        // Set different ticket prices by seat category.
                        show.setPriceMap(generatePriceMap());

                        // Generate default AVAILABLE seats for the show.
                        show.setSeatLayout(generateSeatLayout(show));

                        shows.add(show);
                    }
                }
            }
        }

        // saveAll() inserts all generated shows into MySQL.
        // Cascade settings also save the seats for each show.
        showRepository.saveAll(shows);

        System.out.println("Shows seeded successfully: " + shows.size());
    }

    // Creates the ticket prices used for every sample show.
    // These values match the seat categories in your project.
    private Map<String, Double> generatePriceMap() {

        Map<String, Double> prices = new HashMap<>();

        prices.put("PREMIUM", 15.00);
        prices.put("EXECUTIVE", 12.00);
        prices.put("NORMAL", 10.00);

        return prices;
    }

    // Creates the default seat layout for every show.
    // Rows A-D have 20 seats and Row E has 10 seats.
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