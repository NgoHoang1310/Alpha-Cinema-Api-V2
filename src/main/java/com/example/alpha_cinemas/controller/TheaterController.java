package com.example.alpha_cinemas.controller;

import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.model.Theater;
import com.example.alpha_cinemas.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class TheaterController {

    private final TheaterService theaterService;

    @Autowired
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping("/theaters")
    public ResponseEntity<ApiResponse<Theater>> createMovie(@RequestBody Theater payload) {
        Theater response = theaterService.handleCreateTheater(payload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Created !", response, null));
    }
}
