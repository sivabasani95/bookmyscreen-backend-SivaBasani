package com.example.bookmyscreenbackend.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

// Represents one seat belonging to one movie show.

@Entity
@Table(name = "show_seats" )
public class ShowSeat {

    // Unique ID for each seat record.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Seat row, for example A, B, C, D, or E.
    @Column(name = "row_label", nullable = false)
    private String row;

    // Seat number inside the row.
    @Column(nullable = false)
    private Integer number;

    //Current seat status.
    // AVAILABLE, BOOKED, OR BLOCKED.
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus status = SeatStatus.AVAILABLE;

    // the show this seat belongs to.
    @ManyToOne
    @JoinColumn(name = "show_id", nullable = false)
    @JsonBackReference
    private Show show;

    // JPA requires an empty constructior.
    public ShowSeat(){
    }

    // Getters and setters
    public Long getId() {
        return id;
    }
    public String getRow() {
        return row;
    }

    public void setRow(String row) {
        this.row = row;
    }
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    public SeatStatus getStatus() {
        return status;
    }
    public void setStatus(SeatStatus status) {
        this.status = status;
    }

    public Show getShow() {
        return show;
    }
    public void setShow(Show show) {
        this.show = show;
    }
}























