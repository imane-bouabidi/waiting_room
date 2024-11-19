package com.wora.waiting_room.services.servicesIntr;

import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomCreateDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomUpdateDTO;
import com.wora.waiting_room.services.GenericService;

import java.util.List;

public interface WaitingRoomService extends GenericService<WaitingRoomCreateDTO, WaitingRoomUpdateDTO, WaitingRoomDTO, Long> {
    WaitingRoomDTO save(WaitingRoomCreateDTO createDto);
    WaitingRoomDTO findById(Long id);
    WaitingRoomDTO update(WaitingRoomUpdateDTO updateDto, Long id);
    List<WaitingRoomDTO> findAll(int page, int size);
    void delete(Long id);
}
