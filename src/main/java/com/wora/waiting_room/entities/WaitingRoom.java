package com.wora.waiting_room.entities;

import com.wora.waiting_room.entities.enums.AlgorithmType;
import com.wora.waiting_room.entities.enums.WorkMode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
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
