package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.RoomRequest;
import com.example.alpha_cinemas.model.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoomMapper {
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "theater", ignore = true)
    @Mapping(target = "seats", ignore = true)
    Room toEntity(RoomRequest request);
}
