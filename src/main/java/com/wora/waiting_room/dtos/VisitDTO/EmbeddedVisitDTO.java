package com.wora.waiting_room.dtos.VisitDTO;

import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.entities.enums.Status;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public class EmbeddedVisitDTO {
    private VisitEmbeddedId id;
    @NotNull
    private LocalDateTime arrivalTime;
    @NotNull
    private Status status;
    private byte priority;
    private Integer estimatedProcessingTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
