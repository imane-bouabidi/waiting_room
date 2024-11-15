package com.wora.waiting_room.dtos.VisitorDTO;

import com.wora.waiting_room.entities.Visit;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class VisitorDTO {
    private Long id;
    @NotBlank private String name;
    private List<Visit> visits;
}
