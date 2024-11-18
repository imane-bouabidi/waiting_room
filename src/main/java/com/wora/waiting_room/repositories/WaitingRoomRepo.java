package com.wora.waiting_room.repositories;


import com.wora.waiting_room.entities.WaitingRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingRoomRepo extends JpaRepository<WaitingRoom, Long> {

}

