package com.wora.waiting_room.services.servicesIntr;


import com.wora.waiting_room.dtos.VisitorDTO.VisitorCreateDTO;
import com.wora.waiting_room.dtos.VisitorDTO.VisitorDTO;
import com.wora.waiting_room.dtos.VisitorDTO.VisitorUpdateDTO;
import com.wora.waiting_room.services.GenericService;

import java.util.List;

public interface VisitorService extends GenericService<VisitorCreateDTO, VisitorUpdateDTO, VisitorDTO, Long> {
    VisitorDTO findById(Long id);
    List<VisitorDTO> findAll(int page, int size);
}
