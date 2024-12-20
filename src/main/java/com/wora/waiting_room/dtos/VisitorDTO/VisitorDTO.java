package com.wora.waiting_room.dtos.VisitorDTO;

import com.wora.waiting_room.dtos.VisitDTO.EmbeddedVisitDTO;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorDTO {
    private Long id;
    @NotBlank private String name;
    private List<EmbeddedVisitDTO> visits;
}
