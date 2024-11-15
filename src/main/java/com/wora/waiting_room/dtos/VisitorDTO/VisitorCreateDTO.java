package com.wora.waiting_room.dtos.VisitorDTO;

import jakarta.validation.constraints.NotBlank;

public class VisitorCreateDTO {
    @NotBlank
    private String name;
}
