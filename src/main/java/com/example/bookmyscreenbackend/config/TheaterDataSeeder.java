package com.example.bookmyscreenbackend.config;

import com.example.bookmyscreenbackend.model.Theater;
import com.example.bookmyscreenbackend.repository.TheaterRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
/* “I created a TheaterDataSeeder using CommandLineRunner to automatically add sample theater data when Spring Boot starts.
 I used TheaterRepository and saveAll() to store multiple theaters in MySQL.”*/

// THEATER DATA SEEDER
// Adds sample theater data to MySQL when the application starts.
@Component
public class TheaterDataSeeder implements CommandLineRunner {

    private final TheaterRepository theaterRepository;

    // Constructor injection gives this class access to TheaterRepository.
    // I use the repository to save theater data into MySQL.
    public TheaterDataSeeder(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @Override
    public void run(String... args) {

        // Check whether theater data already exists in the database.
        // This prevents duplicate records every time the application restarts.
        if (theaterRepository.count() > 0) {
            return;
        }

        // Stores each theater brand with its Cloudinary logo URL.
        // These images are uploaded to my own Cloudinary account.
        Map<String, String> logos = Map.of(
                "PVR",
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451505/pvr.avif",

                "INOX",
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451501/inox.avif",

                "Cinepolis",
                "https://res.cloudinary.com/w2mcpaw8/image/upload/v1788451499/cinepolis.avif"
        );

        // This list temporarily stores all Theater objects.
        // At the end, saveAll() saves them together into MySQL.
        List<Theater> theaters = new ArrayList<>();


        // ============================================================
        // ST. LOUIS, MISSOURI
        // Creates sample theaters for different areas in St. Louis.
        // ============================================================

        theaters.add(createTheater(
                "PVR Downtown",
                "Downtown, St. Louis",
                "St. Louis",
                "Missouri",
                logos.get("PVR")
        ));

        theaters.add(createTheater(
                "INOX Central West End",
                "Central West End, St. Louis",
                "St. Louis",
                "Missouri",
                logos.get("INOX")
        ));

        theaters.add(createTheater(
                "Cinepolis Forest Park",
                "Forest Park, St. Louis",
                "St. Louis",
                "Missouri",
                logos.get("Cinepolis")
        ));


        // ============================================================
        // O'FALLON, MISSOURI
        // Adds sample theater locations for the O'Fallon area.
        // ============================================================

        theaters.add(createTheater(
                "PVR O'Fallon",
                "O'Fallon, Missouri",
                "O'Fallon",
                "Missouri",
                logos.get("PVR")
        ));

        theaters.add(createTheater(
                "INOX O'Fallon",
                "O'Fallon, Missouri",
                "O'Fallon",
                "Missouri",
                logos.get("INOX")
        ));

        theaters.add(createTheater(
                "Cinepolis O'Fallon",
                "O'Fallon, Missouri",
                "O'Fallon",
                "Missouri",
                logos.get("Cinepolis")
        ));


        // ============================================================
        // ST. CHARLES, MISSOURI
        // Adds sample theater locations for St. Charles.
        // ============================================================

        theaters.add(createTheater(
                "PVR St. Charles",
                "St. Charles, Missouri",
                "St. Charles",
                "Missouri",
                logos.get("PVR")
        ));

        theaters.add(createTheater(
                "INOX St. Charles",
                "St. Charles, Missouri",
                "St. Charles",
                "Missouri",
                logos.get("INOX")
        ));

        theaters.add(createTheater(
                "Cinepolis St. Charles",
                "St. Charles, Missouri",
                "St. Charles",
                "Missouri",
                logos.get("Cinepolis")
        ));


        // ============================================================
        // CHICAGO, ILLINOIS
        // Adds sample theaters for different Chicago locations.
        // ============================================================

        theaters.add(createTheater(
                "PVR Downtown Chicago",
                "Downtown, Chicago",
                "Chicago",
                "Illinois",
                logos.get("PVR")
        ));

        theaters.add(createTheater(
                "INOX Lincoln Park",
                "Lincoln Park, Chicago",
                "Chicago",
                "Illinois",
                logos.get("INOX")
        ));

        theaters.add(createTheater(
                "Cinepolis River North",
                "River North, Chicago",
                "Chicago",
                "Illinois",
                logos.get("Cinepolis")
        ));


        // ============================================================
        // NEW YORK, NEW YORK
        // Adds sample theaters for different New York locations.
        // ============================================================

        theaters.add(createTheater(
                "PVR Manhattan",
                "Manhattan, New York",
                "New York",
                "New York",
                logos.get("PVR")
        ));

        theaters.add(createTheater(
                "INOX Times Square",
                "Times Square, New York",
                "New York",
                "New York",
                logos.get("INOX")
        ));

        theaters.add(createTheater(
                "Cinepolis Brooklyn",
                "Brooklyn, New York",
                "New York",
                "New York",
                logos.get("Cinepolis")
        ));


        // ============================================================
        // LOS ANGELES, CALIFORNIA
        // Adds sample theaters for different Los Angeles locations.
        // ============================================================

        theaters.add(createTheater(
                "PVR Hollywood",
                "Hollywood, Los Angeles",
                "Los Angeles",
                "California",
                logos.get("PVR")
        ));

        theaters.add(createTheater(
                "INOX Downtown LA",
                "Downtown, Los Angeles",
                "Los Angeles",
                "California",
                logos.get("INOX")
        ));

        theaters.add(createTheater(
                "Cinepolis Beverly Hills",
                "Beverly Hills, Los Angeles",
                "Los Angeles",
                "California",
                logos.get("Cinepolis")
        ));


        // saveAll() saves all Theater objects into the database together.
        // This is cleaner than calling save() separately for every theater.
        theaterRepository.saveAll(theaters);

        System.out.println("Theaters seeded successfully");
    }


    // Helper method creates one Theater object with the given information.
    // It avoids repeating the same setter code for every theater above.
    private Theater createTheater(
            String name,
            String location,
            String city,
            String state,
            String logo) {

        Theater theater = new Theater();

        // Set the theater information before saving it.
        // Each object will become a theater record in MySQL.
        theater.setName(name);
        theater.setLocation(location);
        theater.setCity(city);
        theater.setState(state);
        theater.setLogo(logo);

        return theater;
    }
}