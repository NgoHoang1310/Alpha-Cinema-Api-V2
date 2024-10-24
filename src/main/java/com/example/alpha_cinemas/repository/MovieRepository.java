package com.example.alpha_cinemas.repository;

import com.example.alpha_cinemas.enums.Status;
import com.example.alpha_cinemas.model.Movie;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {
    Movie findMovieById(Long id);

    Set<Movie> findMoviesByDestroyed(boolean destroyed);

    Page<Movie> findMoviesByDestroyedAndStatus(boolean destroyed, Status status, Pageable pageable);

    @Query(
            value = "SELECT DISTINCT m.* FROM movie m JOIN schedule s ON m.id = s.movie_id JOIN room r ON r.id = s.room_id JOIN theater t" +
                    " ON t.id = r.theater_id WHERE m.destroyed = :destroyed AND m.status = :status AND t.district = :theater",
            nativeQuery = true
    )
    Set<Movie> findMoviesByDestroyedAndStatusAndTheater(boolean destroyed, String status, String theater);
}
