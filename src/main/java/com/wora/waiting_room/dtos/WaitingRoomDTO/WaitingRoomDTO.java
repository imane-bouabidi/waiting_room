package com.wora.waiting_room.dtos.WaitingRoomDTO;

import com.wora.waiting_room.dtos.VisitDTO.EmbeddedVisitDTO;
import com.wora.waiting_room.entities.enums.AlgorithmType;
import com.wora.waiting_room.entities.enums.WorkMode;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WaitingRoomDTO {
    private Long id;
    @NotNull
    @Positive
    private Integer date;
    private AlgorithmType algorithmType;
    @Positive
    private Integer Capacity;
    private WorkMode workMode;
    private List<EmbeddedVisitDTO> visits;
}
