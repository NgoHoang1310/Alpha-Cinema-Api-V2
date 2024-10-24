package com.example.alpha_cinemas.service;

import com.example.alpha_cinemas.dto.request.TheaterRequest;
import com.example.alpha_cinemas.model.Theater;

import java.io.IOException;
import java.util.List;

public interface TheaterService {
    Theater handleCreateTheater(TheaterRequest payload) throws IOException;
    List<Theater> handleGetTheaters();
    void handleHardDeleteTheater(Long id);
}
