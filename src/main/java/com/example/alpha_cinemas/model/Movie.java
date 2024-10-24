package com.example.alpha_cinemas.model;

import com.example.alpha_cinemas.enums.Limitation;
import com.example.alpha_cinemas.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "movie")
@Builder
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String thumbPath;
    private Long duration;
    private boolean hot;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "movie_category",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> category = new HashSet<>();
    private String trailer;
    @Enumerated(EnumType.STRING)
    private Limitation limitation;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String language;
    private String director;
    private String cast;
    @Column(name = "description",length = 500)
    private String description;
    private Date releaseDate;
    @Column(name = "destroyed", columnDefinition = "false")
    private boolean destroyed;
    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonIgnore
    private Set<Schedule> schedule = new HashSet<>();
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
