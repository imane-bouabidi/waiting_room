package com.wora.waiting_room.dtos.VisitorDTO;

import jakarta.validation.constraints.NotBlank;

public class EmbeddedVisitorDTO {
    private Long id;
    @NotBlank
    private String name;
}