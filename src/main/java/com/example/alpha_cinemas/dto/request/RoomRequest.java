package com.example.alpha_cinemas.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoomRequest {
    private Long id;
    private String name;
    private Long roomTypeId;
    private Long theaterId;
    private int capacity;
}
