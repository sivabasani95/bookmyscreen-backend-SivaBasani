package com.example.bookmyscreenbackend.model;

// Represents the current booking status of a seat.
public enum SeatStatus {

    // Seat is available and can be selected.
    AVAILABLE,

    // Seat has already been booked.
    BOOKED,

    // Seat is temporarily unavailable.
    BLOCKED
}