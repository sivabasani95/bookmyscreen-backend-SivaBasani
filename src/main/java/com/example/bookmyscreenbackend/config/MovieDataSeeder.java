package com.example.bookmyscreenbackend.config;

import com.example.bookmyscreenbackend.model.Movie;
import com.example.bookmyscreenbackend.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;
import java.time.LocalDate;
import java.util.Arrays;

// Seeds sample movie data into MySQL when the application starts.
// CommandLineRunner automatically runs this code during application startup.
@Component
public class MovieDataSeeder implements CommandLineRunner {

    // Repository is used to save and access movie data in MySQL.
    private final MovieRepository movieRepository;

    // Constructor injection connects MovieRepository to this seedar.
    public MovieDataSeeder(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

// this method automatically runs when the Spring Boot application starts.
    @Override
    public void run(String... args) {

        // Prevents adding the same sample movies every time the application starts.
        if (movieRepository.count() > 0){
            return;
        }

        // Create the first sample movie.
        Movie maa = new Movie();
        maa.setTitle("Maa");
        maa.setDescription("The story of a mother who becomes Kali to end a demonic curse.");

       // Duration is stored in minutes.
        maa.setDuration(135);

        // A movie can have multiple genres.
        maa.setGenre(Arrays.asList(
                "Fantasy",
                "Horror",
                "Mythological",
                "Thriller"
        ));

        // LocalDate stores the movie release date.
        maa.setReleaseDate(LocalDate.of(2026, 9, 14));

        maa.setPosterUrl(
                "https://res.cloudinary.com/amritrajmaurya/image/upload/v1751790461/jn7silixkmp7caq0gpwr.avif"
        );

        maa.setCertificate("UA16+");
        maa.setRating(7.2);
        maa.setIsActive(true);


        // -------------------- MOVIE 2 --------------------
        Movie kannappa = new Movie();

        kannappa.setTitle("Kannappa");
        kannappa.setDescription(
                "The tale of Kannappa, a devoted follower of Lord Shiva."
        );
        kannappa.setDuration(150);

        kannappa.setGenre(Arrays.asList(
                "Action",
                "Mythological"
        ));

        kannappa.setLanguage("Telugu");

        maa.setReleaseDate(LocalDate.of(2026, 9, 21));

        kannappa.setPosterUrl(
                "https://res.cloudinary.com/amritrajmaurya/image/upload/v1751790461/fkbk6wzzxrvbn3ysrums.avif"
        );

        kannappa.setCertificate("UA13+");
        kannappa.setRating(7.3);
        kannappa.setIsActive(true);

// -------------------- MOVIE 3 --------------------
        Movie missionImpossible = new Movie();

        missionImpossible.setTitle(
                "Mission: Impossible - The Final Reckoning"
        );

        missionImpossible.setDescription(
                "Ethan Hunt returns for a high-stakes mission to save the world from impending doom."
        );

        missionImpossible.setDuration(160);

        missionImpossible.setGenre(Arrays.asList(
                "Action",
                "Thriller"
        ));

        missionImpossible.setLanguage("English");
        missionImpossible.setReleaseDate(
                LocalDate.of(2026, 9, 28)
        );

        missionImpossible.setPosterUrl(
                "https://res.cloudinary.com/amritrajmaurya/image/upload/v1751790462/yomilxtf8umhsqekxzvv.avif"
        );

        missionImpossible.setCertificate("UA13+");
        missionImpossible.setRating(8.6);
        missionImpossible.setIsActive(true);

// -------------------- MOVIE 4 --------------------
        Movie f1 = new Movie();
        f1.setTitle("F1: The Movie");
        f1.setDescription(
                "An inside look at the world of Formula 1 racing and its iconic champions."
        );
        f1.setDuration(120);
        f1.setGenre(Arrays.asList(
                "Sports",
                "Documentary"
        ));
        f1.setLanguage("English");
        f1.setReleaseDate(LocalDate.of(2026, 9, 24));

        f1.setPosterUrl(
                "https://res.cloudinary.com/amritrajmaurya/image/upload/v1751790461/psdublbrlv4crojvtzqc.avif"
        );
        f1.setCertificate("UA16+");
        f1.setRating(9.5);
        f1.setIsActive(true);

// -------------------- MOVIE 5 --------------------
        Movie ballerina = new Movie();

        ballerina.setTitle(
                "From the World of John Wick: Ballerina"
        );

        ballerina.setDescription(
                "A ballerina assassin seeks revenge in the dark world of the High Table."
        );

        ballerina.setDuration(130);

        ballerina.setGenre(Arrays.asList(
                "Action",
                "Thriller"
        ));

        ballerina.setLanguage("English");
        ballerina.setReleaseDate(
                LocalDate.of(2026, 9, 1)
        );

        ballerina.setPosterUrl(
                "https://res.cloudinary.com/amritrajmaurya/image/upload/v1751790461/pdw9hxw1xlz1abpyenzc.avif"
        );

        ballerina.setCertificate("A");
        ballerina.setRating(8.7);
        ballerina.setIsActive(true);


        // -------------------- MOVIE 6 --------------------
        Movie metroInDino = new Movie();

        metroInDino.setTitle("Metro In Dino");

        metroInDino.setDescription(
                "Multiple stories of love and life intertwine in the bustling metro city of Mumbai."
        );

        metroInDino.setDuration(130);

        metroInDino.setGenre(Arrays.asList(
                "Romance",
                "Drama"
        ));

        metroInDino.setLanguage("Hindi");
        metroInDino.setReleaseDate(
                LocalDate.of(2026, 9, 2)
        );

        metroInDino.setPosterUrl(
                "https://res.cloudinary.com/amritrajmaurya/image/upload/v1751826680/u4vtkrc4iinsiyjwqrsu.avif"
        );

        metroInDino.setCertificate("UA");
        metroInDino.setRating(7.5);
        metroInDino.setIsActive(true);


        // -------------------- MOVIE 7 --------------------
        Movie dragon = new Movie();

        dragon.setTitle(
                "How to Train Your Dragon: Return of Night Fury"
        );

        dragon.setDescription(
                "Hiccup and Toothless return for a magical journey as a new Night Fury rises."
        );

        dragon.setDuration(105);

        dragon.setGenre(Arrays.asList(
                "Animation",
                "Fantasy",
                "Adventure"
        ));

        dragon.setLanguage("English");

        // Future release date for testing upcoming movies.
        dragon.setReleaseDate(
                LocalDate.of(2026, 9, 4)
        );

        dragon.setPosterUrl(
                "https://res.cloudinary.com/amritrajmaurya/image/upload/v1751826680/lkpu6rs2rxu4jckxtony.avif"
        );

        dragon.setCertificate("UA");
        dragon.setRating(8.8);
        dragon.setIsActive(true);


        // -------------------- MOVIE 8 --------------------
        Movie jurassicPark = new Movie();

        jurassicPark.setTitle("Jurassic Park: Rebirth");

        jurassicPark.setDescription(
                "Dinosaurs return in a world no longer in control — the race for survival begins anew."
        );

        jurassicPark.setDuration(155);

        jurassicPark.setGenre(Arrays.asList(
                "Sci-Fi",
                "Adventure",
                "Action"
        ));

        jurassicPark.setLanguage("English");

        // Future date makes this useful for testing booking.
        jurassicPark.setReleaseDate(
                LocalDate.of(2026, 9, 5)
        );

        jurassicPark.setPosterUrl(
                "https://res.cloudinary.com/amritrajmaurya/image/upload/v1751790815/kw1gearclw4vjmnkxw0o.avif"
        );

        jurassicPark.setCertificate("UA16+");
        jurassicPark.setRating(9.0);
        jurassicPark.setIsActive(true);


        // -------------------- MOVIE 9 --------------------
        Movie sitaareZameenPar = new Movie();

        sitaareZameenPar.setTitle("Sitaare Zameen Par");

        sitaareZameenPar.setDescription(
                "A heartwarming story of a teacher who helps a child discover the star within."
        );

        sitaareZameenPar.setDuration(140);

        sitaareZameenPar.setGenre(Arrays.asList(
                "Drama",
                "Family"
        ));

        sitaareZameenPar.setLanguage("Hindi");
        sitaareZameenPar.setReleaseDate(
                LocalDate.of(2026, 9, 7)
        );

        sitaareZameenPar.setPosterUrl(
                "https://res.cloudinary.com/amritrajmaurya/image/upload/v1751790462/huw3x0efjerh3zxoqtaq.avif"
        );

        sitaareZameenPar.setCertificate("UA");
        sitaareZameenPar.setRating(8.5);
        sitaareZameenPar.setIsActive(true);


        // -------------------- MOVIE 10 --------------------
        Movie m3gan = new Movie();

        m3gan.setTitle("M3GAN 2.0");

        m3gan.setDescription(
                "M3GAN returns with upgraded AI and deadlier instincts in this tech horror sequel."
        );

        m3gan.setDuration(115);

        m3gan.setGenre(Arrays.asList(
                "Horror",
                "Sci-Fi",
                "Thriller"
        ));

        m3gan.setLanguage("English");
        m3gan.setReleaseDate(
                LocalDate.of(2026, 9, 10)
        );

        m3gan.setPosterUrl(
                "https://res.cloudinary.com/amritrajmaurya/image/upload/v1751790461/zfxzrvffdu8zfled6nzt.avif"
        );

        m3gan.setCertificate("A");
        m3gan.setRating(8.4);
        m3gan.setIsActive(true);


        // saveAll() inserts all sample movies into MySQL together.
        movieRepository.saveAll(Arrays.asList(
                maa,
                kannappa,
                missionImpossible,
                f1,
                ballerina,
                metroInDino,
                dragon,
                jurassicPark,
                sitaareZameenPar,
                m3gan
        ));

        // Confirms that the seeding process completed successfully.
        System.out.println("Movies seeded successfully");
    }

    }












