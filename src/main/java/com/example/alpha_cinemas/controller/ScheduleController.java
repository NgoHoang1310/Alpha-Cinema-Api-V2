package com.example.alpha_cinemas.controller;

import com.example.alpha_cinemas.dto.request.ScheduleRequest;
import com.example.alpha_cinemas.dto.response.ApiResponse;
import com.example.alpha_cinemas.dto.response.ScheduleResponse;
import com.example.alpha_cinemas.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/schedules")
    public ResponseEntity<ApiResponse<ScheduleResponse>> createSchedule(@RequestBody ScheduleRequest payload) {
        ScheduleResponse response = scheduleService.handleCreateSchedule(payload);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(HttpStatus.CREATED.value(), "Created !", response, null));
    }

    @PatchMapping("/schedules/{id}")
    public ResponseEntity<ApiResponse<ScheduleResponse>> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequest payload) {
        ScheduleResponse response = scheduleService.handleUpdateSchedule(id, payload);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Created !", response, null));
    }

    @GetMapping("schedules/days")
    public ResponseEntity<ApiResponse<Set<String>>> findScheduleDays() {
        Set<String> response = scheduleService.handleFindScheduleDays();
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Successfully !", response, null));
    }

    @GetMapping(value = "schedules/days", params = "movie")
    public ResponseEntity<ApiResponse<Set<String>>> findScheduleDaysByMovie(@RequestParam("movie") Long id) {
        Set<String> response = scheduleService.handleFindScheduleDaysByMovie(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Successfully !", response, null));
    }

    @GetMapping(value = "schedules", params = {"day", "movie", "theater"})
    public ResponseEntity<ApiResponse<ScheduleResponse>> findScheduleByDateAndMovie
            (
                    @RequestParam("day") LocalDate day,
                    @RequestParam("movie") Long id,
                    @RequestParam("theater") String theater

            ) {
        ScheduleResponse response = scheduleService.handleFindScheduleByDateAndMovieAndTheater(day, id, theater);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Successfully !", response, null));
    }

    @GetMapping(value = "schedules", params = {"day", "province", "theater"})
    public ResponseEntity<ApiResponse<Set<ScheduleResponse>>> findSchedulesByDay
            (@RequestParam("day") LocalDate date,
             @RequestParam("province") String province,
             @RequestParam("theater") String theater) {
        Set<ScheduleResponse> response = scheduleService.handleFindSchedulesByDateAndTheater(date, province, theater);
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ApiResponse<>(HttpStatus.OK.value(), "Successfully !", response, null));
    }
}
