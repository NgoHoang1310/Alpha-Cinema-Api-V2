package com.example.alpha_cinemas.controller;

import com.example.alpha_cinemas.dto.request.SeatRequest;
import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.model.Room;
import com.example.alpha_cinemas.model.RoomType;
import com.example.alpha_cinemas.model.Seat;
import com.example.alpha_cinemas.model.SeatType;
import com.example.alpha_cinemas.service.RoomService;
import com.example.alpha_cinemas.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class SeatController {
    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @PostMapping("/seats")
    public ResponseEntity<ApiResponse<Seat>> createSeats(@RequestBody SeatRequest payload){
        Seat response = seatService.handleCreateSeats(payload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Created !", response, null));
    }

    @PostMapping("/seats/type")
    public ResponseEntity<ApiResponse<SeatType>> createSeatType(@RequestBody SeatType payload){
        SeatType response = seatService.handleCreateSeatType(payload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Created !", response, null));
    }

    @GetMapping("seats/room/{id}")
    public ResponseEntity<ApiResponse<Set<Seat>>> getSeatsByMovie(@PathVariable("id") Long id){
        Set<Seat> response = seatService.handleGetSeatsByRoom(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Ok !", response, null));
    }
}
