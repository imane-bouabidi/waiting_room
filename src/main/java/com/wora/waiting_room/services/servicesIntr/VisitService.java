package com.wora.waiting_room.services.servicesIntr;

import com.wora.waiting_room.dtos.VisitDTO.VisitCreateDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitUpdateDTO;
import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.services.GenericService;

import java.time.Duration;
import java.util.List;

public interface VisitService extends GenericService<VisitCreateDTO, VisitUpdateDTO, VisitDTO, VisitEmbeddedId> {
//    VisitDTO save(VisitCreateDTO createDto);
//    VisitDTO findById(VisitEmbeddedId id);
//    VisitDTO update(VisitUpdateDTO updateDto, VisitEmbeddedId id);
    List<VisitDTO> findAll(int page, int size);
    VisitDTO inProgress(Long visitorId,Long waitingRoomId);
    VisitDTO finishVisit(Long visitorId,Long waitingRoomId);
    VisitDTO cancelVisit(Long visitorId,Long waitingRoomId);
    Duration calculateAverageWaitTime();
//    void delete(VisitEmbeddedId id);
}

