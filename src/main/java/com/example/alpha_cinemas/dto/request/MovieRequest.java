package com.example.alpha_cinemas.dto.request;

import com.example.alpha_cinemas.enums.Limitation;
import com.example.alpha_cinemas.enums.Status;
import com.example.alpha_cinemas.model.Category;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovieRequest {
    @NotNull(message = "You must enter title !")
    private String title;
    private MultipartFile file;
    @NotNull(message = "You must enter duration !")
    private Long duration;
    private boolean hot;
    @NotNull(message = "You must enter category !")
    private Set<String> category = new HashSet<>();
    private String trailer;
    @NotNull(message = "You must enter limitation !")
    private Limitation limitation;
    private Status status;
    private String language;
    private String director;
    private String cast;
    private String description;
    @NotNull(message = "You must enter release day !")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date releaseDate;
    private boolean destroyed;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
