package com.wora.waiting_room.dtos.VisitorDTO;

import com.wora.waiting_room.dtos.VisitDTO.EmbeddedVisitDTO;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VisitorDTO {
    private Long id;
    @NotBlank private String name;
    private List<EmbeddedVisitDTO> visits;
}
