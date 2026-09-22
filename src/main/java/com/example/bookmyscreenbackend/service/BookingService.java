package com.example.bookmyscreenbackend.service;

import com.example.bookmyscreenbackend.dto.BookingRequest;
import com.example.bookmyscreenbackend.model.Booking;
import com.example.bookmyscreenbackend.model.SeatStatus;
import com.example.bookmyscreenbackend.model.Show;
import com.example.bookmyscreenbackend.model.ShowSeat;
import com.example.bookmyscreenbackend.model.User;
import com.example.bookmyscreenbackend.repository.BookingRepository;
import com.example.bookmyscreenbackend.repository.ShowRepository;
import com.example.bookmyscreenbackend.repository.ShowSeatRepository;
import com.example.bookmyscreenbackend.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

// Handles the business logic for creating and retrieving bookings.
@Service
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final ShowRepository showRepository;
    private final ShowSeatRepository showSeatRepository;

    // Constructor injection.
    public BookingService(
            BookingRepository bookingRepository,
            UserRepository userRepository,
            ShowRepository showRepository,
            ShowSeatRepository showSeatRepository
    ) {
        this.bookingRepository = bookingRepository;
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
    }


    // ==========================================================
    // CREATE BOOKING
    // ==========================================================

    // Creates a booking for the currently logged-in user.
    @Transactional
    public Booking createBooking(
            String email,
            BookingRequest request
    ) {

        // Find the logged-in user.
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );


        // Find the selected show.
        Show show = showRepository.findById(request.getShowId())
                .orElseThrow(() ->
                        new RuntimeException("Show not found")
                );


        // Make sure seats were selected.
        if (request.getSeatIds() == null ||
                request.getSeatIds().isEmpty()) {

            throw new RuntimeException(
                    "Please select at least one seat"
            );
        }


        // Get selected seats from MySQL.
        List<ShowSeat> selectedSeats =
                showSeatRepository.findAllById(
                        request.getSeatIds()
                );


        // Make sure all selected seats exist.
        if (selectedSeats.size() !=
                request.getSeatIds().size()) {

            throw new RuntimeException(
                    "One or more selected seats were not found"
            );
        }


        // ======================================================
        // VALIDATE SEATS
        // ======================================================

        for (ShowSeat seat : selectedSeats) {

            // Make sure the seat belongs to this show.
            if (!seat.getShow()
                    .getId()
                    .equals(show.getId())) {

                throw new RuntimeException(
                        "One or more selected seats do not belong to this show"
                );
            }


            // Make sure the seat is still available.
            if (seat.getStatus() !=
                    SeatStatus.AVAILABLE) {

                throw new RuntimeException(
                        "Seat " +
                                seat.getRow() +
                                seat.getNumber() +
                                " is no longer available"
                );
            }
        }


        // ======================================================
        // CALCULATE PRICE
        // ======================================================

        double ticketAmount =
                calculateTicketAmount(
                        show,
                        selectedSeats
                );


        // 5% convenience fee.
        double convenienceFee =
                ticketAmount * 0.05;


        // Final booking amount.
        double totalAmount =
                ticketAmount + convenienceFee;


        // ======================================================
        // MARK SEATS AS BOOKED
        // ======================================================

        for (ShowSeat seat : selectedSeats) {

            seat.setStatus(
                    SeatStatus.BOOKED
            );
        }


        showSeatRepository.saveAll(
                selectedSeats
        );


        // ======================================================
        // CREATE BOOKING
        // ======================================================

        Booking booking =
                new Booking();


        booking.setUser(user);

        booking.setShow(show);

        booking.setSeats(
                selectedSeats
        );

        booking.setTicketAmount(
                ticketAmount
        );

        booking.setConvenienceFee(
                convenienceFee
        );

        booking.setTotalAmount(
                totalAmount
        );

        booking.setPaymentMethod(
                request.getPaymentMethod()
        );

        booking.setPaymentId(
                request.getPaymentId()
        );

        booking.setStatus(
                "CONFIRMED"
        );


        // Save booking in MySQL.
        return bookingRepository.save(
                booking
        );
    }


    // ==========================================================
    // GET USER BOOKINGS
    // ==========================================================

    public List<Booking> getBookingsForUser(
            String email
    ) {

        // Find logged-in user.
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );


        // Return newest bookings first.
        return bookingRepository
                .findByUserIdOrderByBookingDateTimeDesc(
                        user.getId()
                );
    }


    // ==========================================================
    // CALCULATE TICKET AMOUNT
    // ==========================================================

    private double calculateTicketAmount(
            Show show,
            List<ShowSeat> selectedSeats
    ) {

        // Get ticket prices for this show.
        Map<String, Double> prices =
                show.getPriceMap();


        // Make sure prices exist.
        if (prices == null ||
                prices.isEmpty()) {

            throw new RuntimeException(
                    "Ticket prices are not configured for this show"
            );
        }


        double total = 0.0;


        // Calculate each seat separately.
        for (ShowSeat seat : selectedSeats) {

            String seatType;


            // Row A = NORMAL.
            if ("A".equalsIgnoreCase(
                    seat.getRow())) {

                seatType = "NORMAL";
            }

            // Rows B, C and D = EXECUTIVE.
            else if (
                    "B".equalsIgnoreCase(seat.getRow()) ||
                            "C".equalsIgnoreCase(seat.getRow()) ||
                            "D".equalsIgnoreCase(seat.getRow())
            ) {

                seatType = "EXECUTIVE";
            }

            // Row E = PREMIUM.
            else if ("E".equalsIgnoreCase(
                    seat.getRow())) {

                seatType = "PREMIUM";
            }

            else {

                throw new RuntimeException(
                        "Unknown seat row: " +
                                seat.getRow()
                );
            }


            // Get price for this seat category.
            Double seatPrice =
                    prices.get(seatType);


            // Make sure that category has a price.
            if (seatPrice == null) {

                throw new RuntimeException(
                        "Ticket price is not configured for " +
                                seatType +
                                " seats"
                );
            }


            // Add this seat's price.
            total += seatPrice;
        }


        return total;
    }
}