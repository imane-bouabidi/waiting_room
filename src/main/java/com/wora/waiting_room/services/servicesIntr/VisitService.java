package com.wora.waiting_room.services.servicesIntr;

import com.wora.waiting_room.dtos.VisitDTO.VisitCreateDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitUpdateDTO;
import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.services.GenericService;

import java.util.List;

public interface VisitService extends GenericService<VisitCreateDTO, VisitUpdateDTO, VisitDTO, VisitEmbeddedId> {
    VisitDTO save(VisitCreateDTO createDto);
    VisitDTO findById(VisitEmbeddedId id);
    VisitDTO update(VisitUpdateDTO updateDto, VisitEmbeddedId id);
    List<VisitDTO> findAll();
    void delete(VisitEmbeddedId id);
}

