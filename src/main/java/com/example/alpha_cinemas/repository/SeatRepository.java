package com.example.alpha_cinemas.repository;

import com.example.alpha_cinemas.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
    @Query(value = "SELECT s.* FROM seat s LEFT JOIN room r ON s.room_id = r.id WHERE r.id = :id", nativeQuery = true)
    Set<Seat> findSeatsByRoom(Long id);
}
