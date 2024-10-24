package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.ScheduleRequest;
import com.example.alpha_cinemas.dto.response.ScheduleResponse;
import com.example.alpha_cinemas.model.Schedule;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    Schedule toEntity(ScheduleRequest request);
    ScheduleResponse toDto(Schedule schedule);
}
