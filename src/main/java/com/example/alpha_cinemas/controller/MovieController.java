package com.example.alpha_cinemas.controller;

import com.example.alpha_cinemas.dto.request.MovieRequest;
import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.dto.response.MovieResponse;
import com.example.alpha_cinemas.enums.Status;
import com.example.alpha_cinemas.service.MovieService;
import com.example.alpha_cinemas.dto.request.PaginationRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

@RestController
public class MovieController {

    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/movies")
    public ResponseEntity<ApiResponse<Set<MovieResponse>>> getMovies() {
        Set<MovieResponse> response = movieService.handleGetMovies();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Ok !", response, null));
    }

    @GetMapping("/movie-detail/{id}")
    public ResponseEntity<ApiResponse<MovieResponse>> getMovieAMovie(@PathVariable Long id) {
       MovieResponse response = movieService.handleGetAMovie(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Ok !", response, null));
    }

    @GetMapping(value = "/movies", params = "status")
    public ResponseEntity<ApiResponse<?>> getMoviesByStatus
            (@RequestParam("status") Status status,
             @ModelAttribute PaginationRequest paginationRequest
            ) {
        Map<String, Object> response = movieService.handleGetMoviesByStatus(status, paginationRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Ok !", response.get("content"), response.get("pagination")));
    }

    @PostMapping(value = "/movies")
    public ResponseEntity<ApiResponse<MovieResponse>> createMovie(@Valid @ModelAttribute MovieRequest payload) throws IOException {
        MovieResponse response = movieService.handleCreateMovie(payload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Created !", response, null));
    }

    @PatchMapping("/movies/{id}")
    public ResponseEntity<ApiResponse<MovieResponse>> updateMovie(@Valid @ModelAttribute MovieRequest payload, @PathVariable Long id) {
        MovieResponse response = movieService.handleUpdateMovie(id, payload);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Updated !", response, null));
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<ApiResponse<?>> deleteMovie(@PathVariable Long id) {
        movieService.handleDeleteMovie(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Deleted !", null, null));
    }

}
