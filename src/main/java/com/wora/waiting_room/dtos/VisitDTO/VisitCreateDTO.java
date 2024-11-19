package com.wora.waiting_room.dtos.VisitDTO;

import com.wora.waiting_room.dtos.VisitorDTO.EmbeddedVisitorDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.EmbeddedWaitingRoomDTO;
import com.wora.waiting_room.entities.enums.Status;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitCreateDTO {
    @NotNull
    private LocalDateTime arrivalTime;
    @NotNull
    private Status status;
    private byte priority;
    private Integer estimatedProcessingTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private EmbeddedVisitorDTO embeddedVisitorDTO;
    private EmbeddedWaitingRoomDTO embeddedWaitingRoomDTO;


}
