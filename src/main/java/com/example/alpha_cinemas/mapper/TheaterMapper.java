package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.TheaterRequest;
import com.example.alpha_cinemas.model.Theater;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TheaterMapper {
    Theater toEntity(TheaterRequest request);
}
