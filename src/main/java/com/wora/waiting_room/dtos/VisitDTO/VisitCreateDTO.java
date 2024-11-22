package com.wora.waiting_room.dtos.VisitDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wora.waiting_room.dtos.VisitorDTO.EmbeddedVisitorDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.EmbeddedWaitingRoomDTO;
import com.wora.waiting_room.entities.enums.Status;
import jakarta.validation.constraints.NotNull;

import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitCreateDTO {
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime arrivalTime;
    @NotNull
    private Status status;
    private byte priority;
    private Duration estimatedProcessingTime;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long visitorId;
    private Long waitingRoomId;


}
