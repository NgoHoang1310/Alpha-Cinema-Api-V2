package com.example.alpha_cinemas.repository;

import com.example.alpha_cinemas.dto.response.ScheduleResponse;
import com.example.alpha_cinemas.model.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findSchedulesByDate(LocalDate date);

    @Query(value = "SELECT date FROM schedule WHERE date >= :currentDate", nativeQuery = true)
    Set<String> findScheduleDays(LocalDate currentDate);

    @Query(value = "SELECT movie_id, date, time, room_id FROM schedule WHERE movie_id = :movieId AND date = :date AND time = :time AND room_id = :roomId", nativeQuery = true)
    Object findExitedSchedule(Long movieId, LocalDate date, Set<LocalTime> time, Long roomId);

    @Query(value = "SELECT s.* FROM schedule s LEFT JOIN room r ON s.room_id = r.id LEFT JOIN theater t ON r.theater_id = t.id WHERE s.date=:date AND t.province = :province AND t.district = :theater  ", nativeQuery = true)
    Set<Schedule> findSchedulesByDateAndTheater(LocalDate date, String province, String theater);

//    @Query(value = "SELECT DATE_FORMAT(st.time, '%H:%i') FROM schedule s JOIN movie m ON s.movie_id = m.id JOIN schedule_times st ON st.schedule_id = s.id WHERE s.date= :date AND m.id = :id", nativeQuery = true)
//    Set<String> findTimesByDateAndMovie(LocalDate date, Long id);

    @Query(value = "SELECT DISTINCT s.* FROM schedule s JOIN movie m ON s.movie_id = m.id" +
            " JOIN schedule_times st ON st.schedule_id = s.id JOIN room r ON s.room_id = r.id" +
            " JOIN theater t ON r.theater_id = t.id" +
            " WHERE s.date= :date AND m.id = :id AND t.district = :theater", nativeQuery = true)
    Schedule findScheduleByDateAndMovieAndTheater(LocalDate date, Long id, String theater);

    @Query(value = "SELECT s.date FROM schedule s JOIN movie m ON s.movie_id = m.id" +
            " WHERE date >= :currentDate AND m.id = :id", nativeQuery = true)
    Set<String> findScheduleDaysByMovie(LocalDate currentDate, Long id);


}
