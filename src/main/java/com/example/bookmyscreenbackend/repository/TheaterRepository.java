package com.example.bookmyscreenbackend.repository;

import com.example.bookmyscreenbackend.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
// Connects Theater with MySQL
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    // Find theaters by state, ignoring upper/lower case.
    List<Theater> findByStateContainingIgnoreCase(String state);


}
