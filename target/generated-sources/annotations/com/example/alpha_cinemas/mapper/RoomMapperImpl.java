package com.example.alpha_cinemas.mapper;

import com.example.alpha_cinemas.dto.request.RoomRequest;
import com.example.alpha_cinemas.model.Room;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-10-21T21:06:06+0700",
    comments = "version: 1.6.0, compiler: javac, environment: Java 22.0.2 (Oracle Corporation)"
)
@Component
public class RoomMapperImpl implements RoomMapper {

    @Override
    public Room toEntity(RoomRequest request) {
        if ( request == null ) {
            return null;
        }

        Room.RoomBuilder room = Room.builder();

        room.id( request.getId() );
        room.name( request.getName() );
        room.capacity( request.getCapacity() );

        return room.build();
    }
}
