package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.TheaterRequest;
import com.example.alpha_cinemas.model.Theater;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-21T21:06:06+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class TheaterMapperImpl implements TheaterMapper {

    @Override
    public Theater toEntity(TheaterRequest request) {
        if ( request == null ) {
            return null;
        }

        Theater.TheaterBuilder theater = Theater.builder();

        theater.id( request.getId() );
        theater.province( request.getProvince() );
        theater.district( request.getDistrict() );
        theater.other( request.getOther() );

        return theater.build();
    }
}
