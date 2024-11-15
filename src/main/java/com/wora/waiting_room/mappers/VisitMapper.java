package com.wora.waiting_room.mappers;

import com.wora.waiting_room.dtos.VisitDTO.VisitCreateDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitUpdateDTO;
import com.wora.waiting_room.entities.Visit;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface VisitMapper{
    Visit toEntity(VisitDTO dto);
    Visit toEntity(VisitUpdateDTO dto);
    Visit toEntity(VisitCreateDTO dto);
    VisitDTO toDto(Visit visit);
}
