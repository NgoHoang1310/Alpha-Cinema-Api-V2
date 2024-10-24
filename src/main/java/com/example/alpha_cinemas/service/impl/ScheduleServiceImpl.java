package com.example.alpha_cinemas.service.impl;

import com.example.alpha_cinemas.dto.request.ScheduleRequest;
import com.example.alpha_cinemas.dto.response.ScheduleResponse;
import com.example.alpha_cinemas.enums.ApiErrorCode;
import com.example.alpha_cinemas.exception.ApiException;
import com.example.alpha_cinemas.mapper.ScheduleMapper;
import com.example.alpha_cinemas.model.Movie;
import com.example.alpha_cinemas.model.Room;
import com.example.alpha_cinemas.model.Schedule;
import com.example.alpha_cinemas.repository.MovieRepository;
import com.example.alpha_cinemas.repository.RoomRepository;
import com.example.alpha_cinemas.repository.ScheduleRepository;
import com.example.alpha_cinemas.service.ScheduleService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final MovieRepository movieRepository;
    private final RoomRepository roomRepository;
    private final ScheduleMapper scheduleMapper;

    @Autowired
    public ScheduleServiceImpl
            (ScheduleRepository scheduleRepository,
             MovieRepository movieRepository,
             RoomRepository roomRepository,
             ScheduleMapper scheduleMapper
            ) {
        this.scheduleRepository = scheduleRepository;
        this.movieRepository = movieRepository;
        this.roomRepository = roomRepository;
        this.scheduleMapper = scheduleMapper;
    }


    @Override
    public ScheduleResponse handleCreateSchedule(ScheduleRequest payload) {
        Movie movie = movieRepository.findById(payload.getMovieId())
                .orElseThrow(() -> new ApiException(ApiErrorCode.MOVIE_NOT_FOUND));

        Room room = roomRepository.findById(payload.getRoomId())
                .orElseThrow(() -> new ApiException(ApiErrorCode.ROOM_NOT_FOUND));

//        Object exitedSchedule = scheduleRepository.findExitedSchedule(payload.getMovieId(), payload.getDate(), payload.getTime(), payload.getRoomId());
//        if (exitedSchedule != null) throw new ApiException(ApiErrorCode.SCHEDULE_EXISTED);
        Schedule schedule = scheduleMapper.toEntity(payload);
        schedule.setMovie(movie);
        schedule.setRoom(room);

        return scheduleMapper.toDto(scheduleRepository.save(schedule));
    }

    @Override
    public Set<String> handleFindScheduleDays() {
        LocalDate currentDate = LocalDate.now();
        return scheduleRepository
                .findScheduleDays(currentDate);
    }

    @Override
    public Set<String> handleFindScheduleDaysByMovie(Long id) {
        LocalDate currentDate = LocalDate.now();
        return scheduleRepository
                .findScheduleDaysByMovie(currentDate, id);
    }

    @Override
    public List<ScheduleResponse> handleFindSchedulesByDate(LocalDate date) {
        List<ScheduleResponse> schedulesResponse = new ArrayList<>();
        List<Schedule> schedules = scheduleRepository.findSchedulesByDate(date);
        if (schedules != null) {
            schedules.stream()
                    .map((schedule -> schedulesResponse.add(scheduleMapper.toDto(schedule))))
                    .collect(Collectors.toList());
        }

        return schedulesResponse;
    }

    @Override
    public Set<ScheduleResponse> handleFindSchedulesByDateAndTheater(LocalDate date, String province, String theater) {
        Set<Schedule> schedules = scheduleRepository.findSchedulesByDateAndTheater(date, province, theater);
        return schedules.stream().map((scheduleMapper::toDto)).collect(Collectors.toSet());
    }

    @Override
    public ScheduleResponse handleFindScheduleByDateAndMovieAndTheater(LocalDate date, Long id, String theater) {
        return scheduleMapper.toDto(scheduleRepository.findScheduleByDateAndMovieAndTheater(date, id, theater));

    }

    @Override
    public ScheduleResponse handleUpdateSchedule(Long id, ScheduleRequest payload) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ApiException(ApiErrorCode.SCHEDULE_NOT_FOUND));
        Movie movie = movieRepository.findById(payload.getMovieId())
                .orElseThrow(() -> new ApiException(ApiErrorCode.MOVIE_NOT_FOUND));
        Room room = roomRepository.findById(payload.getRoomId())
                .orElseThrow(() -> new ApiException(ApiErrorCode.ROOM_NOT_FOUND));

        schedule.setMovie(movie);
        schedule.setDate(payload.getDate());
        schedule.setTimes(payload.getTimes());
        schedule.setRoom(room);
        return scheduleMapper.toDto(scheduleRepository.save(schedule));
    }


}
