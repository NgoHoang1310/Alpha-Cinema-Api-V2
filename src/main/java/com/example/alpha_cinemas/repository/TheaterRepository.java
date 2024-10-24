package com.example.alpha_cinemas.repository;

import com.example.alpha_cinemas.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TheaterRepository extends JpaRepository<Theater, Long> {
    @Query(value = "SELECT * FROM theater", nativeQuery = true)
    List<Theater> findAllGroupByProvince();
}
