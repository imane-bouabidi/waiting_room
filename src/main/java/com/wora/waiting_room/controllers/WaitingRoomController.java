package com.wora.waiting_room.controllers;

import com.wora.waiting_room.dtos.VisitDTO.EmbeddedVisitDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomCreateDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomUpdateDTO;
import com.wora.waiting_room.entities.enums.AlgorithmType;
import com.wora.waiting_room.services.servicesImpl.WaitingRoomServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/waiting-rooms")
@RequiredArgsConstructor
public class WaitingRoomController {

    private final WaitingRoomServiceImpl waitingRoomService;

    @PostMapping
    public ResponseEntity<WaitingRoomDTO> createWaitingRoom(@RequestBody @Valid WaitingRoomCreateDTO createDto) {
        WaitingRoomDTO waitingRoomDTO = waitingRoomService.save(createDto);
        return ResponseEntity.status(201).body(waitingRoomDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WaitingRoomDTO> getWaitingRoom(@PathVariable Long id) {
        WaitingRoomDTO waitingRoomDTO = waitingRoomService.findById(id);
        if (waitingRoomDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(waitingRoomDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WaitingRoomDTO> updateWaitingRoom(@RequestBody @Valid WaitingRoomUpdateDTO updateDto, @PathVariable Long id) {
        WaitingRoomDTO updatedWaitingRoom = waitingRoomService.update(updateDto, id);
        return ResponseEntity.ok(updatedWaitingRoom);
    }

    @GetMapping
    public ResponseEntity<List<WaitingRoomDTO>> getAllWaitingRooms(@RequestParam int page, @RequestParam int size) {
        List<WaitingRoomDTO> waitingRooms = waitingRoomService.findAll(page, size);
        return ResponseEntity.ok(waitingRooms);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWaitingRoom(@PathVariable Long id) {
        waitingRoomService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{waitingRoomId}/sorted")
    public ResponseEntity<List<EmbeddedVisitDTO>> getSortedVisitsByAlgorithm(
            @PathVariable Long waitingRoomId) {

        List<EmbeddedVisitDTO> sortedVisits = waitingRoomService.getSortedVisitsByAlgorithm(waitingRoomId);

        if (sortedVisits.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(sortedVisits);
    }
}

