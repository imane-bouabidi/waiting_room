package com.wora.waiting_room.dtos.VisitorDTO;

import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VisitorCreateDTO {
    @NotBlank
    private String name;
}
