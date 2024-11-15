package com.wora.waiting_room.dtos.VisitorDTO;

import com.wora.waiting_room.entities.Visit;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public class VisitorCreateDTO {
    @NotBlank
    private String name;
}
