package com.example.alpha_cinemas.service;

import com.example.alpha_cinemas.dto.request.ScheduleRequest;
import com.example.alpha_cinemas.dto.response.ScheduleResponse;
import com.example.alpha_cinemas.model.Schedule;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ScheduleService {
    ScheduleResponse handleCreateSchedule(ScheduleRequest payload);

    Set<String> handleFindScheduleDays();
    Set<String> handleFindScheduleDaysByMovie(Long id);

    List<ScheduleResponse> handleFindSchedulesByDate(LocalDate date);
    Set<ScheduleResponse> handleFindSchedulesByDateAndTheater(LocalDate date, String province, String theater);
    ScheduleResponse handleFindScheduleByDateAndMovieAndTheater(LocalDate date, Long id, String theater);

    ScheduleResponse handleUpdateSchedule(Long id, ScheduleRequest payload);
}
