package com.wora.waiting_room.dtos.VisitDTO;

import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.entities.enums.Status;
import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmbeddedVisitDTO {
    private VisitEmbeddedId id;
    @NotNull
    private LocalDateTime arrivalTime;
    @NotNull
    private Status status;
    private Byte priority;
    private Duration estimatedProcessingTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
