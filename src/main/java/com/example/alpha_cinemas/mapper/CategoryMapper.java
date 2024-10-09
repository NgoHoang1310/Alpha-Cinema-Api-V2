package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.CategoryRequest;
import com.example.alpha_cinemas.model.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Category toEntity(CategoryRequest request);
}
