package com.wora.waiting_room.dtos.WaitingRoomDTO;

import com.wora.waiting_room.entities.Visit;
import com.wora.waiting_room.entities.enums.AlgorithmType;
import com.wora.waiting_room.entities.enums.WorkMode;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public class WaitingRoomCreateDTO {
    @NotNull
    @Positive
    private Integer date;
    private AlgorithmType algorithmType;
    @Positive
    private Integer Capacity;
    private WorkMode workMode;
}
