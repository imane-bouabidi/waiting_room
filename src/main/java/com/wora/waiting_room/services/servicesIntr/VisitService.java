package com.wora.waiting_room.services.servicesIntr;

import com.wora.waiting_room.dtos.VisitDTO.VisitCreateDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitUpdateDTO;
import com.wora.waiting_room.services.GenericService;

import java.util.List;

public interface VisitService extends GenericService<VisitCreateDTO, VisitUpdateDTO, VisitDTO, Long> {
    VisitDTO save(VisitCreateDTO createDto);
    VisitDTO findById(Long id);
    VisitDTO update(VisitUpdateDTO updateDto, Long id);
    List<VisitDTO> findAll();
    void delete(Long id);
}

