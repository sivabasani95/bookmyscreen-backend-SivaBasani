package com.example.bookmyscreenbackend.repository;

import com.example.bookmyscreenbackend.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

// Handles database operations for bookings.
public interface BookingRepository extends JpaRepository<Booking, Long> {

    // Finds all bookings for a specific user.
    List<Booking> findByUserIdOrderByBookingDateTimeDesc(Long userId);
}