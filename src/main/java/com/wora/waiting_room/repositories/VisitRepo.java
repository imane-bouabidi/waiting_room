package com.wora.waiting_room.repositories;

import com.wora.waiting_room.entities.Visit;
import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitRepo extends JpaRepository<Visit, VisitEmbeddedId> {

}
