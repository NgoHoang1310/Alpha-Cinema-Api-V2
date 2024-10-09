package com.example.alpha_cinemas.model;

import com.example.alpha_cinemas.enums.RoomType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Room extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int name;
    private RoomType type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;
    private int capacity;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<Seat> seats = new HashSet<>();

}
