package com.wora.waiting_room.mappers;

import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomCreateDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomUpdateDTO;
import com.wora.waiting_room.entities.WaitingRoom;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WaitingRoomMapper {
    WaitingRoom toEntity(WaitingRoomDTO dto);
    WaitingRoom toEntity(WaitingRoomUpdateDTO dto);
    WaitingRoom toEntity(WaitingRoomCreateDTO dto);
    WaitingRoomDTO toDto(WaitingRoom waitingRoom);
}

