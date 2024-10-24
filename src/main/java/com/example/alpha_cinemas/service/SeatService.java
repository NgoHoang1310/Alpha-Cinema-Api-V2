package com.example.alpha_cinemas.service;

import com.example.alpha_cinemas.dto.request.SeatRequest;
import com.example.alpha_cinemas.model.Seat;
import com.example.alpha_cinemas.model.SeatType;

import java.util.Set;

public interface SeatService {

    Seat handleCreateSeats(SeatRequest payload);

    SeatType handleCreateSeatType(SeatType payload);

    Set<Seat> handleGetSeatsByRoom(Long id);

}
