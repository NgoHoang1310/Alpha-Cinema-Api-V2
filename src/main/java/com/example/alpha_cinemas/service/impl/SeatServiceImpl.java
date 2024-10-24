package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.dto.request.SeatRequest;
import com.example.alpha_cinemas.enums.ApiErrorCode;
import com.example.alpha_cinemas.exception.ApiException;
import com.example.alpha_cinemas.mapper.SeatMapper;
import com.example.alpha_cinemas.model.Room;
import com.example.alpha_cinemas.model.Seat;
import com.example.alpha_cinemas.model.SeatType;
import com.example.alpha_cinemas.repository.RoomRepository;
import com.example.alpha_cinemas.repository.SeatRepository;
import com.example.alpha_cinemas.repository.SeatTypeRepository;
import com.example.alpha_cinemas.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class SeatServiceImpl implements SeatService {
    private final SeatRepository seatRepository;
    private final SeatTypeRepository seatTypeRepository;
    private final RoomRepository roomRepository;
    private final SeatMapper seatMapper;


    @Autowired
    public SeatServiceImpl
            (SeatRepository seatRepository,
             SeatTypeRepository seatTypeRepository,
             RoomRepository roomRepository,
             SeatMapper seatMapper
            ) {
        this.seatRepository = seatRepository;
        this.seatTypeRepository = seatTypeRepository;
        this.roomRepository = roomRepository;
        this.seatMapper = seatMapper;
    }




    @Override
    public Seat handleCreateSeats(SeatRequest payload){
        SeatType seatType = seatTypeRepository.findById(payload.getSeatTypeId())
                .orElseThrow(() -> new ApiException(ApiErrorCode.USER_NOT_FOUND));
        Room room = roomRepository.findById(payload.getRoomId())
                .orElseThrow(() -> new ApiException(ApiErrorCode.USER_NOT_FOUND));

        Seat seat = seatMapper.toEntity(payload);
        seat.setType(seatType);
        seat.setRoom(room);
        return seatRepository.save(seat);
    }

    @Override
    public SeatType handleCreateSeatType(SeatType payload){
        return  seatTypeRepository.save(payload);
    }

    @Override
    public Set<Seat> handleGetSeatsByRoom(Long id){
        return seatRepository.findSeatsByRoom(id);
    }
}
