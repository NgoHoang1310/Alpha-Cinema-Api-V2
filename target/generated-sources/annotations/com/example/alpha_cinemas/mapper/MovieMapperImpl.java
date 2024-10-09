package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.MovieRequest;
import com.example.alpha_cinemas.dto.response.MovieResponse;
import com.example.alpha_cinemas.model.Category;
import com.example.alpha_cinemas.model.Movie;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-09T18:14:29+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class MovieMapperImpl implements MovieMapper {

    @Override
    public Movie toEntity(MovieRequest request) {
        if ( request == null ) {
            return null;
        }

        Movie.MovieBuilder movie = Movie.builder();

        movie.category( map( request.getCategory() ) );
        movie.title( request.getTitle() );
        movie.duration( request.getDuration() );
        movie.hot( request.isHot() );
        movie.trailer( request.getTrailer() );
        movie.limitation( request.getLimitation() );
        movie.status( request.getStatus() );
        movie.language( request.getLanguage() );
        movie.director( request.getDirector() );
        movie.cast( request.getCast() );
        movie.description( request.getDescription() );
        movie.releaseDate( request.getReleaseDate() );
        movie.destroyed( request.isDestroyed() );
        movie.createdAt( request.getCreatedAt() );
        movie.updatedAt( request.getUpdatedAt() );

        return movie.build();
    }

    @Override
    public MovieResponse toDto(Movie movie) {
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
