package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.SeatRequest;
import com.example.alpha_cinemas.model.Seat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SeatMapper {
    @Mapping(target = "type", ignore = true)
    @Mapping(target = "room", ignore = true)
    Seat toEntity(SeatRequest request);
}
