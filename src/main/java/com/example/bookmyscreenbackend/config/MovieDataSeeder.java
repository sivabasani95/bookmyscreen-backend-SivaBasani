package com.example.bookmyscreenbackend.config;

import com.example.bookmyscreenbackend.model.Movie;
import com.example.bookmyscreenbackend.repository.MovieRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Arrays;

@Component
public class MovieDataSeeder implements CommandLineRunner {

    private final MovieRepository movieRepository;

    public MovieDataSeeder(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public void run(String... args) {

        // Prevent duplicate seed data
        if (movieRepository.count() > 0) {
            return;
        }

        // -------------------- MOVIE 1 --------------------
        Movie maa = new Movie();

        maa.setTitle("Maa");

        maa.setDescription(
                "The story of a mother who becomes Kali to end a demonic curse."
        );

        maa.setDuration(135);

        maa.setGenre(Arrays.asList(
                "Fantasy",
                "Horror",
                "Mythological",
                "Thriller"
        ));

        maa.setLanguage("Hindi");

        maa.setReleaseDate(
                LocalDate.of(2026, 9, 14)
        );

        maa.setPosterUrl(
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451501/m1.avif"
        );

        maa.setCertificate("UA16+");
        maa.setRating(7.2);
        maa.setIsActive(true);


        // -------------------- MOVIE 2 --------------------
        Movie kannappa = new Movie();

        kannappa.setTitle("Kannappa");

        kannappa.setDescription(
                "A mythological tale of devotion and sacrifice, following the journey of Kannappa."
        );

        kannappa.setDuration(150);

        kannappa.setGenre(Arrays.asList(
                "Action",
                "Mythological"
        ));

        kannappa.setLanguage("Telugu");

        kannappa.setReleaseDate(
                LocalDate.of(2026, 9, 21)
        );

        kannappa.setPosterUrl(
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451502/m2.avif"
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
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451502/m3.avif"
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

        f1.setReleaseDate(
                LocalDate.of(2026, 9, 24)
        );

        f1.setPosterUrl(
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451502/m4.avif"
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
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451502/m5.avif"
        );

        ballerina.setCertificate("A");
        ballerina.setRating(8.7);
        ballerina.setIsActive(true);


        // -------------------- MOVIE 6 --------------------
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
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451503/m6.avif"
        );

        m3gan.setCertificate("A");
        m3gan.setRating(8.4);
        m3gan.setIsActive(true);


        // -------------------- MOVIE 7 --------------------
        Movie housefull5 = new Movie();

        housefull5.setTitle("Housefull 5");

        housefull5.setDescription(
                "A comedy adventure filled with confusion, unexpected twists, and hilarious situations."
        );

        housefull5.setDuration(145);

        housefull5.setGenre(Arrays.asList(
                "Comedy",
                "Drama"
        ));

        housefull5.setLanguage("Hindi");

        housefull5.setReleaseDate(
                LocalDate.of(2026, 9, 12)
        );

        housefull5.setPosterUrl(
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451503/m7.avif"
        );

        housefull5.setCertificate("UA13+");
        housefull5.setRating(6.1);
        housefull5.setIsActive(true);


        // -------------------- MOVIE 8 --------------------
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
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451503/m8.avif"
        );

        sitaareZameenPar.setCertificate("UA");
        sitaareZameenPar.setRating(8.5);
        sitaareZameenPar.setIsActive(true);


        // -------------------- MOVIE 9 --------------------
        Movie naruto = new Movie();

        naruto.setTitle("Naruto");

        naruto.setDescription(
                "Naruto and his friends face a powerful new enemy during an action-packed ninja adventure."
        );

        naruto.setDuration(120);

        naruto.setGenre(Arrays.asList(
                "Animation",
                "Action",
                "Adventure"
        ));

        naruto.setLanguage("Japanese");

        naruto.setReleaseDate(
                LocalDate.of(2026, 9, 15)
        );

        naruto.setPosterUrl(
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451504/m9.avif"
        );

        naruto.setCertificate("UA13+");
        naruto.setRating(9.6);
        naruto.setIsActive(true);


        // -------------------- MOVIE 10 --------------------
        Movie twentyEightYearsLater = new Movie();

        twentyEightYearsLater.setTitle("28 Years Later");

        twentyEightYearsLater.setDescription(
                "Survivors face a dangerous new chapter in a world devastated by a deadly infection."
        );

        twentyEightYearsLater.setDuration(125);

        twentyEightYearsLater.setGenre(Arrays.asList(
                "Horror",
                "Thriller",
                "Drama"
        ));

        twentyEightYearsLater.setLanguage("English");

        twentyEightYearsLater.setReleaseDate(
                LocalDate.of(2026, 9, 18)
        );

        twentyEightYearsLater.setPosterUrl(
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451504/m10.avif"
        );

        twentyEightYearsLater.setCertificate("A");
        twentyEightYearsLater.setRating(7.9);
        twentyEightYearsLater.setIsActive(true);


        // -------------------- MOVIE 11 --------------------
        Movie sinners = new Movie();

        sinners.setTitle("Sinners");

        sinners.setDescription(
                "Two brothers return home hoping for a fresh start but encounter a terrifying supernatural threat."
        );

        sinners.setDuration(135);

        sinners.setGenre(Arrays.asList(
                "Horror",
                "Thriller",
                "Drama"
        ));

        sinners.setLanguage("English");

        sinners.setReleaseDate(
                LocalDate.of(2026, 9, 20)
        );

        sinners.setPosterUrl(
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451504/m11.avif"
        );

        sinners.setCertificate("A");
        sinners.setRating(8.0);
        sinners.setIsActive(true);


        // -------------------- MOVIE 12 --------------------
        Movie kesariChapter2 = new Movie();

        kesariChapter2.setTitle("Kesari Chapter 2");

        kesariChapter2.setDescription(
                "A historical drama centered on courage, justice, and the fight against oppression."
        );

        kesariChapter2.setDuration(140);

        kesariChapter2.setGenre(Arrays.asList(
                "Drama",
                "History"
        ));

        kesariChapter2.setLanguage("Hindi");

        kesariChapter2.setReleaseDate(
                LocalDate.of(2026, 9, 22)
        );

        kesariChapter2.setPosterUrl(
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451504/m12.avif"
        );

        kesariChapter2.setCertificate("UA13+");
        kesariChapter2.setRating(8.2);
        kesariChapter2.setIsActive(true);


        // Save all 12 movies to MySQL
        movieRepository.saveAll(Arrays.asList(
                maa,
                kannappa,
                missionImpossible,
                f1,
                ballerina,
                m3gan,
                housefull5,
                sitaareZameenPar,
                naruto,
                twentyEightYearsLater,
                sinners,
                kesariChapter2
        ));

        System.out.println("Movies seeded successfully");
    }
}