package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.dto.request.TheaterRequest;
import com.example.alpha_cinemas.mapper.TheaterMapper;
import com.example.alpha_cinemas.model.Theater;
import com.example.alpha_cinemas.repository.TheaterRepository;
import com.example.alpha_cinemas.service.FirebaseStorageService;
import com.example.alpha_cinemas.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TheaterServiceImpl implements TheaterService {

    private final TheaterRepository theaterRepository;
    private final TheaterMapper theaterMapper;
    private final FirebaseStorageService firebaseStorageService;


    @Autowired
    public TheaterServiceImpl(TheaterRepository theaterRepository, TheaterMapper theaterMapper, FirebaseStorageService firebaseStorageService) {
        this.theaterRepository = theaterRepository;
        this.theaterMapper = theaterMapper;
        this.firebaseStorageService = firebaseStorageService;
    }

    @Override
    public Theater handleCreateTheater(TheaterRequest payload) throws IOException {
        String imageUrl = "";
        if (!payload.getFile().isEmpty()) {
            String imgName = firebaseStorageService.save(payload.getFile(), "theaters");
            imageUrl = firebaseStorageService.getImageUrl(imgName);
        }
        Theater theater = theaterMapper.toEntity(payload);
        theater.setImage(imageUrl);
        return theaterRepository.save(theater);
    }

    @Override
    public List<Theater> handleGetTheaters(){
        return theaterRepository.findAllGroupByProvince();
    }

    @Override
    public void handleHardDeleteTheater(Long id) {
        theaterRepository.deleteById(id);
    }
}
