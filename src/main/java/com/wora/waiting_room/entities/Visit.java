package com.wora.waiting_room.entities;

import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.entities.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Visit {
    @EmbeddedId
    private VisitEmbeddedId id;

    @NotNull(message = "Arrival time is mandatory")
    private LocalDateTime arrivalTime;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Status is mandatory")
    private Status status;

    @Min(value = 1, message = "Priority must be at least 1")
    private byte priority;

    @Min(value = 1, message = "Estimated processing time must be positive")
    private Duration estimatedProcessingTime;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToOne
    @MapsId("visitorId")
    @JoinColumn(name = "visitor_id", nullable = false)
    private Visitor visitor;

    @ManyToOne
    @MapsId("waitingRoomId")
    @JoinColumn(name = "waiting_room_id", nullable = false)
    private WaitingRoom waitingRoom;
}
