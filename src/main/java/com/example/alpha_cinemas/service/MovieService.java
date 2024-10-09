package com.example.alpha_cinemas.service;

import com.example.alpha_cinemas.dto.request.MovieRequest;
import com.example.alpha_cinemas.dto.response.MovieResponse;
import com.example.alpha_cinemas.enums.Status;
import com.example.alpha_cinemas.dto.request.PaginationRequest;
import com.example.alpha_cinemas.model.Movie;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface MovieService {
    Set<MovieResponse> handleGetMovies();
    MovieResponse handleGetAMovie(Long id);
    Map<String, Object> handleGetMoviesByStatus(Status status, PaginationRequest paginationRequest);
    MovieResponse handleCreateMovie(MovieRequest payload) throws IOException;
    MovieResponse handleUpdateMovie(Long id,MovieRequest payload);
    void handleDeleteMovie(Long id);
}