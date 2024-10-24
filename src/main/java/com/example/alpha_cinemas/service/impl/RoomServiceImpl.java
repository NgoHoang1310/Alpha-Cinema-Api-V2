package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.dto.request.RoomRequest;
import com.example.alpha_cinemas.enums.ApiErrorCode;
import com.example.alpha_cinemas.exception.ApiException;
import com.example.alpha_cinemas.mapper.RoomMapper;
import com.example.alpha_cinemas.model.Room;
import com.example.alpha_cinemas.model.RoomType;
import com.example.alpha_cinemas.model.Theater;
import com.example.alpha_cinemas.repository.RoomRepository;
import com.example.alpha_cinemas.repository.RoomTypeRepository;
import com.example.alpha_cinemas.repository.TheaterRepository;
import com.example.alpha_cinemas.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final RoomTypeRepository roomTypeRepository;
    private final TheaterRepository theaterRepository;
    private final RoomMapper roomMapper;


    @Autowired
    public RoomServiceImpl
            (RoomRepository roomRepository,
             RoomTypeRepository roomTypeRepository,
             TheaterRepository theaterRepository,
             RoomMapper roomMapper
            ) {
        this.roomRepository = roomRepository;
        this.roomTypeRepository = roomTypeRepository;
        this.theaterRepository = theaterRepository;
        this.roomMapper = roomMapper;
    }

    @Override
    public Room handleCreateRoom(RoomRequest payload) {
        RoomType roomType = roomTypeRepository.findById(payload.getRoomTypeId())
                .orElseThrow(() -> new ApiException(ApiErrorCode.USER_NOT_FOUND));
        Theater theater = theaterRepository.findById(payload.getTheaterId())
                .orElseThrow(() -> new ApiException(ApiErrorCode.USER_NOT_FOUND));

        Room room = roomMapper.toEntity(payload);
        room.setType(roomType);
        room.setTheater(theater);

        return roomRepository.save(room);
    }

    @Override
    public RoomType handleCreateRoomType(RoomType payload) {
        return roomTypeRepository.save(payload);
    }
}
