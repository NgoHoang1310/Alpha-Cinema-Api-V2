package com.example.alpha_cinemas.controller;

import com.example.alpha_cinemas.dto.request.RoomRequest;
import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.model.Room;
import com.example.alpha_cinemas.model.RoomType;
import com.example.alpha_cinemas.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RoomController {
    private final RoomService roomService;

    @Autowired
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @PostMapping("/rooms")
    public ResponseEntity<ApiResponse<Room>> createRoom(@RequestBody RoomRequest payload){
        Room response = roomService.handleCreateRoom(payload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Created !", response, null));
    }

    @PostMapping("/rooms/type")
    public ResponseEntity<ApiResponse<RoomType>> createRoomType(@RequestBody RoomType payload){
        RoomType response = roomService.handleCreateRoomType(payload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Created !", response, null));
    }
}
