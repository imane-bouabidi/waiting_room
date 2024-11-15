package com.wora.waiting_room.mappers;

import com.wora.waiting_room.dtos.VisitorDTO.VisitorCreateDTO;
import com.wora.waiting_room.dtos.VisitorDTO.VisitorDTO;
import com.wora.waiting_room.dtos.VisitorDTO.VisitorUpdateDTO;
import com.wora.waiting_room.entities.Visitor;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitorMapper{
    Visitor toEntity(VisitorDTO dto);
    Visitor toEntity(VisitorUpdateDTO dto);
    Visitor toEntity(VisitorCreateDTO dto);
    VisitorDTO toDto(Visitor visitor);
}
