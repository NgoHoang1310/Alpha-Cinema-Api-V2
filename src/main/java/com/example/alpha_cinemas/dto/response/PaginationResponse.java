package com.example.alpha_cinemas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Pageable;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaginationResponse {
    private Pageable pageable;
    private int totalPages;
    private long totalElements;
    private boolean first;
    private boolean last;

}
