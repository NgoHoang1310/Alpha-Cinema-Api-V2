package com.example.alpha_cinemas.controller;

import com.example.alpha_cinemas.dto.request.TheaterRequest;
import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.model.Theater;
import com.example.alpha_cinemas.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;


@RestController
public class TheaterController {

    private final TheaterService theaterService;

    @Autowired
    public TheaterController(TheaterService theaterService) {
        this.theaterService = theaterService;
    }

    @PostMapping("/theaters")
    public ResponseEntity<ApiResponse<Theater>> createMovie(@ModelAttribute TheaterRequest payload) throws IOException {
        Theater response = theaterService.handleCreateTheater(payload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Created !", response, null));
    }

    @GetMapping("/theaters")
    public ResponseEntity<ApiResponse<List<Theater>>> getTheaters(){
        List<Theater> response = theaterService.handleGetTheaters();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Succesfully !", response, null));
    }

    @DeleteMapping(value = "/theaters/{id}", params = "type")
    public ResponseEntity<ApiResponse<?>> hardDeleteTheater(@PathVariable("id") Long id, @RequestParam("type") String type){
        if (type.equals("soft")) {
//            theaterService.handleSoftDeleteMovie(id);
        }else {
            theaterService.handleHardDeleteTheater(id);
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Deleted !", null, null));
    }
}
