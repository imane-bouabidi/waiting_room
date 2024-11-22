package com.wora.waiting_room.repositories;

import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.entities.Visit;
import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitRepo extends JpaRepository<Visit, VisitEmbeddedId> {
    List<Visit> findByWaitingRoomId(Long waitingRoomId);

}
