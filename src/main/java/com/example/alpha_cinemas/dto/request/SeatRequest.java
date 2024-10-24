package com.example.alpha_cinemas.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeatRequest {
    private Long id;
    private String seat;
    private int number;
    private Long seatTypeId;
    private Long roomId;
    private String status;
}
