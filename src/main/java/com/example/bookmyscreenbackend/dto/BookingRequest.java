package com.example.bookmyscreenbackend.dto;

import java.util.List;

// Receives booking information from the frontend.
public class BookingRequest {

    // ID of the show the user selected.
    private Long showId;

    // IDs of the seats selected by the user.
    private List<Long> seatIds;

    // Payment method used by the user.
    private String paymentMethod;

    // Payment reference returned after successful payment.
    private String paymentId;


    // Required empty constructor.
    public BookingRequest() {
    }


    // Returns the selected show ID.
    public Long getShowId() {
        return showId;
    }

    // Sets the selected show ID.
    public void setShowId(Long showId) {
        this.showId = showId;
    }

    // Returns the selected seat IDs.
    public List<Long> getSeatIds() {
        return seatIds;
    }

    // Sets the selected seat IDs.
    public void setSeatIds(List<Long> seatIds) {
        this.seatIds = seatIds;
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
}