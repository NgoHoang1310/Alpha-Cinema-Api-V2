package com.example.alpha_cinemas.repository;

import com.example.alpha_cinemas.enums.Status;
import com.example.alpha_cinemas.model.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {
    Movie findMovieById(Long id);
    Set<Movie> findMoviesByDestroyed(boolean destroyed);
    Page<Movie> findMoviesByDestroyedAndStatus(boolean destroyed, Status status, Pageable pageable);
}
