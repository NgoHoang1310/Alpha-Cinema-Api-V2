package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.model.Theater;
import com.example.alpha_cinemas.repository.TheaterRepository;
import com.example.alpha_cinemas.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;

    @Autowired
    public TheaterServiceImpl(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    @Override
    public Theater handleCreateTheater(Theater payload){
        return theaterRepository.save(payload);
    }
}
