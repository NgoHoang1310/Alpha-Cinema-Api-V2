package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.ScheduleRequest;
import com.example.alpha_cinemas.dto.response.MovieResponse;
import com.example.alpha_cinemas.dto.response.ScheduleResponse;
import com.example.alpha_cinemas.model.Category;
import com.example.alpha_cinemas.model.Movie;
import com.example.alpha_cinemas.model.Schedule;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-21T21:06:06+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class ScheduleMapperImpl implements ScheduleMapper {

    @Override
    public Schedule toEntity(ScheduleRequest request) {
        if ( request == null ) {
            return null;
        }

        Schedule.ScheduleBuilder schedule = Schedule.builder();

        schedule.id( request.getId() );
        schedule.date( request.getDate() );
        Set<LocalTime> set = request.getTimes();
        if ( set != null ) {
            schedule.times( new LinkedHashSet<LocalTime>( set ) );
        }

        return schedule.build();
    }

    @Override
    public ScheduleResponse toDto(Schedule schedule) {
        if ( schedule == null ) {
            return null;
        }

        ScheduleResponse scheduleResponse = new ScheduleResponse();

        scheduleResponse.setId( schedule.getId() );
        scheduleResponse.setMovie( movieToMovieResponse( schedule.getMovie() ) );
        scheduleResponse.setDate( schedule.getDate() );
        Set<LocalTime> set = schedule.getTimes();
        if ( set != null ) {
            scheduleResponse.setTimes( new LinkedHashSet<LocalTime>( set ) );
        }
        scheduleResponse.setRoom( schedule.getRoom() );
        scheduleResponse.setCreatedAt( schedule.getCreatedAt() );
        scheduleResponse.setUpdatedAt( schedule.getUpdatedAt() );

        return scheduleResponse;
    }

    protected MovieResponse movieToMovieResponse(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieResponse movieResponse = new MovieResponse();

        movieResponse.setId( movie.getId() );
        movieResponse.setTitle( movie.getTitle() );
        movieResponse.setThumbPath( movie.getThumbPath() );
        movieResponse.setDuration( movie.getDuration() );
        movieResponse.setHot( movie.isHot() );
        Set<Category> set = movie.getCategory();
        if ( set != null ) {
            movieResponse.setCategory( new LinkedHashSet<Category>( set ) );
        }
        movieResponse.setTrailer( movie.getTrailer() );
        movieResponse.setLimitation( movie.getLimitation() );
        movieResponse.setStatus( movie.getStatus() );
        movieResponse.setLanguage( movie.getLanguage() );
        movieResponse.setDirector( movie.getDirector() );
        movieResponse.setCast( movie.getCast() );
        movieResponse.setDescription( movie.getDescription() );
        movieResponse.setReleaseDate( movie.getReleaseDate() );
        movieResponse.setCreatedAt( movie.getCreatedAt() );
        movieResponse.setUpdatedAt( movie.getUpdatedAt() );

        return movieResponse;
    }
}
