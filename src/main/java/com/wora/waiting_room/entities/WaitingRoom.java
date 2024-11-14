package com.wora.waiting_room.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class WaitingRoom {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Positive
    @Column(name = "date", nullable = false)
    private Integer date;

    @Column(name = "algorithm_type", nullable = true)
    @Enumerated(EnumType.STRING)
    private AlgorithmType algorithmType;

    @Positive
    @Column(name = "capacity", nullable = true)
    private Integer Capacity;

    @Column(name = "workMode", nullable = true)
    @Enumerated(EnumType.STRING)
    private WorkMode workMode;

    @OneToMany(mappedBy = "waitingRoom", cascade = CascadeType.ALL)
    private List<Visit> visits;
}
