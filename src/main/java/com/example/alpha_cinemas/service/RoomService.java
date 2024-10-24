package com.example.alpha_cinemas.service;

import com.example.alpha_cinemas.dto.request.RoomRequest;
import com.example.alpha_cinemas.model.Room;
import com.example.alpha_cinemas.model.RoomType;

public interface RoomService {
    Room handleCreateRoom(RoomRequest payload);
    RoomType handleCreateRoomType(RoomType payload);
}
