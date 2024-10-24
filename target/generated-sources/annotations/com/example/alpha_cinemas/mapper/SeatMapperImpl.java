package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.SeatRequest;
import com.example.alpha_cinemas.model.Seat;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-21T21:06:06+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class SeatMapperImpl implements SeatMapper {

    @Override
    public Seat toEntity(SeatRequest request) {
        if ( request == null ) {
            return null;
        }

        Seat.SeatBuilder seat = Seat.builder();

        seat.id( request.getId() );
        seat.seat( request.getSeat() );
        seat.number( request.getNumber() );
        seat.status( request.getStatus() );

        return seat.build();
    }
}
