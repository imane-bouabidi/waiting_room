package com.wora.waiting_room.repositories;

import com.wora.waiting_room.entities.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitorRepo extends JpaRepository<Visitor, Long> {}

