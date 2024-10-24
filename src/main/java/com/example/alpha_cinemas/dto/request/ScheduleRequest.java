package com.example.alpha_cinemas.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ScheduleRequest {
    private Long id;
    @NotNull(message = "You must enter movie id !")
    @Digits(integer = 6, fraction = 2, message = "Movie id must be integer !")
    private Long movieId;
    @NotNull(message = "You must enter date !")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @NotNull(message = "You must enter time !")
    @JsonFormat(pattern = "HH:mm")
    private Set<LocalTime> times;
    @NotNull(message = "You must enter movie id !")
    @Digits(integer = 6, fraction = 2, message = "Room id must be integer !")
    private Long roomId;
}
