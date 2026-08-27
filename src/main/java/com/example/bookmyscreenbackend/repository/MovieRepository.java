package com.example.bookmyscreenbackend.repository;
import com.example.bookmyscreenbackend.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

// Repository connects our Movie entity to the MySQL database.
// JpaRepository gives us built-in CRUD methods such as:
// save(), findAll(), findById(), and deleteById().
public interface MovieRepository extends JpaRepository<Movie, Long> {

}
