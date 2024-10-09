package com.example.alpha_cinemas.dto.response;

import com.example.alpha_cinemas.enums.Limitation;
import com.example.alpha_cinemas.enums.Status;
import com.example.alpha_cinemas.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieResponse {
    private Long id;
    private String title;
    private String thumbPath;
    private Long duration;
    private boolean hot;
    private Set<Category> category = new HashSet<>();
    private String trailer;
    private Limitation limitation;
    private Status status;
    private String language;
    private String director;
    private String cast;
    private String description;
    @JsonFormat(pattern = "dd/MM/yyyy", timezone = "Asia/Ho_Chi_Minh")
    private Date releaseDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime createdAt;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDateTime updatedAt;

    public Set<String> getCategory() {
        return this.category.stream()
                .map(Category::getName).collect(Collectors.toSet());
    }

}
