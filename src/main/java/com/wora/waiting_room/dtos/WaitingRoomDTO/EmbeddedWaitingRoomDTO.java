package com.wora.waiting_room.dtos.WaitingRoomDTO;

import com.wora.waiting_room.entities.enums.AlgorithmType;
import com.wora.waiting_room.entities.enums.WorkMode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public class EmbeddedWaitingRoomDTO {
    private Long id;
    @NotNull
    @Positive
    private Integer date;
    private AlgorithmType algorithmType;
    @Positive
    private Integer Capacity;
    private WorkMode workMode;
}
