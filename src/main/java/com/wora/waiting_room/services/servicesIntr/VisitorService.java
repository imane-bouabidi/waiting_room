package com.wora.waiting_room.services.servicesIntr;


import com.wora.waiting_room.dtos.VisitorDTO.VisitorCreateDTO;
import com.wora.waiting_room.dtos.VisitorDTO.VisitorDTO;
import com.wora.waiting_room.dtos.VisitorDTO.VisitorUpdateDTO;
import com.wora.waiting_room.services.GenericService;

import java.util.List;

public interface VisitorService extends GenericService<VisitorCreateDTO, VisitorUpdateDTO, VisitorDTO, Long> {
    VisitorDTO save(VisitorCreateDTO createDto);
    VisitorDTO findById(Long id);
    VisitorDTO update(VisitorUpdateDTO updateDto, Long id);
    List<VisitorDTO> findAll();
    void delete(Long id);
}
