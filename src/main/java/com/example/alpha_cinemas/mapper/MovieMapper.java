package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.MovieRequest;
import com.example.alpha_cinemas.dto.response.MovieResponse;
import com.example.alpha_cinemas.model.Category;
import com.example.alpha_cinemas.model.Movie;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    @Mapping(target = "category", source = "category")
    Movie toEntity(MovieRequest request);

    MovieResponse toDto(Movie movie);

    default Set<Category> map(Set<String> categories) {
        return categories.stream()
                .map(item -> Category.builder().name(item).build()).collect(Collectors.toSet());
    }
}
