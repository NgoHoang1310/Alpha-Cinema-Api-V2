package com.example.alpha_cinemas.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaginationRequest {
    private Integer page = 0;
    private Integer limit = 10;
    private String sortBy = "id";
    private String orderBy = "DESC";
}
