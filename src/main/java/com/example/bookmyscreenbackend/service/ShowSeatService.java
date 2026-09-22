

package com.example.bookmyscreenbackend.service;

import com.example.bookmyscreenbackend.model.ShowSeat;
import com.example.bookmyscreenbackend.repository.ShowSeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;

// Handles business logic related to show seats.
@Service
public class ShowSeatService {

    // Repository used to access show seat data.
    private final ShowSeatRepository showSeatRepository;

    // Constructor injection provides the repository.
    public ShowSeatService(
            ShowSeatRepository showSeatRepository) {

        this.showSeatRepository = showSeatRepository;
    }


    // Gets all seats belonging to one show.
    public List<ShowSeat> getSeatsByShowId(Long showId) {

        return showSeatRepository.findByShowId(showId);
    }
}