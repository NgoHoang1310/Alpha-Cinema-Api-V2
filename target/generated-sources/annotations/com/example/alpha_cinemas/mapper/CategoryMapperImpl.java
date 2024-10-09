package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.CategoryRequest;
import com.example.alpha_cinemas.model.Category;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-10T00:20:35+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryRequest request) {
        if ( request == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.id( request.getId() );
        category.name( request.getName() );

        return category.build();
    }
}
