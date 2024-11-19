package com.wora.waiting_room.dtos.VisitorDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VisitorUpdateDTO {
    private Long id;
    @NotBlank
    private String name;
}
