package com.example.bookmyscreenbackend.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Represents one completed movie booking made by a user.
@Entity
@Table(name = "bookings")
public class Booking {

    // Unique ID for each booking.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // User who made this booking.
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Show selected by the user.
    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    private Show show;

    // Seats included in this booking.
    @ManyToMany
    @JoinTable(
            name = "booking_seats",
            joinColumns = @JoinColumn(name = "booking_id"),
            inverseJoinColumns = @JoinColumn(name = "show_seat_id")
    )
    private List<ShowSeat> seats = new ArrayList<>();

    // Total ticket amount before fees.
    @Column(nullable = false)
    private Double ticketAmount;

    // Extra convenience fee or taxes.
    @Column(nullable = false)
    private Double convenienceFee;

    // Final amount paid by the user.
    @Column(nullable = false)
    private Double totalAmount;

    // Stores the payment method used for the booking.
    private String paymentMethod;

    // Stores the payment reference returned after payment.
    private String paymentId;

    // Stores the current booking status.
    private String status;

    // Stores when the booking was created.
    private LocalDateTime bookingDateTime;


    // Required empty constructor for JPA.
    public Booking() {
    }


    // Automatically sets the booking time before saving.
    @PrePersist
    public void onCreate() {
        bookingDateTime = LocalDateTime.now();
    }


    // Returns the booking ID.
    public Long getId() {
        return id;
    }

    // Sets the booking ID.
    public void setId(Long id) {
        this.id = id;
    }

    // Returns the user who made the booking.
    public User getUser() {
        return user;
    }

    // Sets the user who made the booking.
    public void setUser(User user) {
        this.user = user;
    }

    // Returns the selected show.
    public Show getShow() {
        return show;
    }

    // Sets the selected show.
    public void setShow(Show show) {
        this.show = show;
    }

    // Returns all seats in the booking.
    public List<ShowSeat> getSeats() {
        return seats;
    }

    // Sets the seats for the booking.
    public void setSeats(List<ShowSeat> seats) {
        this.seats = seats;
    }

    // Returns the ticket amount.
    public Double getTicketAmount() {
        return ticketAmount;
    }

    // Sets the ticket amount.
    public void setTicketAmount(Double ticketAmount) {
        this.ticketAmount = ticketAmount;
    }

    // Returns the convenience fee.
    public Double getConvenienceFee() {
        return convenienceFee;
    }

    // Sets the convenience fee.
    public void setConvenienceFee(Double convenienceFee) {
        this.convenienceFee = convenienceFee;
    }

    // Returns the final total amount.
    public Double getTotalAmount() {
        return totalAmount;
    }

    // Sets the final total amount.
    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    // Returns the payment method.
    public String getPaymentMethod() {
        return paymentMethod;
    }

    // Sets the payment method.
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    // Returns the payment ID.
    public String getPaymentId() {
        return paymentId;
    }

    // Sets the payment ID.
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    // Returns the booking status.
    public String getStatus() {
        return status;
    }

    // Sets the booking status.
    public void setStatus(String status) {
        this.status = status;
    }

    // Returns the date and time when the booking was created.
    public LocalDateTime getBookingDateTime() {
        return bookingDateTime;
    }

    // Sets the booking date and time.
    public void setBookingDateTime(LocalDateTime bookingDateTime) {
        this.bookingDateTime = bookingDateTime;
    }
}