package com.wora.waiting_room.dtos.VisitDTO;

import com.wora.waiting_room.dtos.WaitingRoomDTO.EmbeddedWaitingRoomDTO;
import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.entities.enums.Status;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class VisitDTO {
    private VisitEmbeddedId id;
    @NotNull
    private LocalDateTime arrivalTime;
    @NotNull
    private Status status;
    private byte priority;
    private Integer estimatedProcessingTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private EmbeddedVisitDTO embeddedVisitDTO;
    private EmbeddedWaitingRoomDTO embeddedWaitingRoomDTO;
}
