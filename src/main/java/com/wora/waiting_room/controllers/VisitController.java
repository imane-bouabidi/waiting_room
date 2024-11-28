package com.wora.waiting_room.controllers;

import com.wora.waiting_room.dtos.VisitDTO.VisitCreateDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitUpdateDTO;
import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.services.servicesImpl.VisitServiceImpl;
import com.wora.waiting_room.services.servicesIntr.VisitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.util.List;

@RestController
@RequestMapping("/api/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitService visitService;


    @PostMapping
    public ResponseEntity<VisitDTO> createVisit(@RequestBody @Valid VisitCreateDTO visitCreateDTO) {
        VisitDTO savedVisit = visitService.save(visitCreateDTO);
        return ResponseEntity.status(201).body(savedVisit);
    }

    @GetMapping("/{visitorId}/{waitingRoomId}")
    public ResponseEntity<VisitDTO> getVisitById(
            @PathVariable("visitorId") Long visitorId,
            @PathVariable("waitingRoomId") Long waitingRoomId) {

        VisitEmbeddedId id = new VisitEmbeddedId(visitorId, waitingRoomId);
        VisitDTO visitDTO = visitService.findById(id);

        if (visitDTO == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(visitDTO);
    }

    @PutMapping("/{visitorId}/{waitingRoomId}")
    public ResponseEntity<VisitDTO> updateVisit(
            @RequestBody @Valid VisitUpdateDTO visitUpdateDTO,
            @PathVariable("visitorId") Long visitorId,
            @PathVariable("waitingRoomId") Long waitingRoomId) {

        VisitEmbeddedId id = new VisitEmbeddedId(visitorId, waitingRoomId);
        VisitDTO updatedVisit = visitService.update(visitUpdateDTO, id);

        if (updatedVisit == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(updatedVisit);
    }

    @GetMapping
    public ResponseEntity<List<VisitDTO>> getAllVisits(@RequestParam int page, @RequestParam int size) {
        List<VisitDTO> visits = visitService.findAll(page,size);
        return ResponseEntity.ok(visits);
    }

    @GetMapping("/averageTime")
    public ResponseEntity<Duration> getAverageTime() {
        Duration averageTime = visitService.calculateAverageWaitTime();
        return ResponseEntity.ok(averageTime);
    }


    @DeleteMapping("/{visitorId}/{waitingRoomId}")
    public ResponseEntity<Void> deleteVisit(
            @PathVariable("visitorId") Long visitorId,
            @PathVariable("waitingRoomId") Long waitingRoomId) {

        VisitEmbeddedId id = new VisitEmbeddedId(visitorId, waitingRoomId);
        visitService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/in-progress/{visitorId}/{waitingRoomId}")
    public ResponseEntity<VisitDTO> startVisit(@PathVariable Long visitorId, @PathVariable Long waitingRoomId) {
        VisitDTO updatedVisit = visitService.inProgress(visitorId,waitingRoomId);
        return ResponseEntity.ok(updatedVisit);
    }

    @PutMapping("/finish/{visitorId}/{waitingRoomId}")
    public ResponseEntity<VisitDTO> finishVisit(@PathVariable Long visitorId, @PathVariable Long waitingRoomId) {
        VisitDTO updatedVisit = visitService.finishVisit(visitorId,waitingRoomId);
        return ResponseEntity.ok(updatedVisit);
    }

    @PutMapping("/cancel/{visitorId}/{waitingRoomId}")
    public ResponseEntity<VisitDTO> cancelVisit(@PathVariable Long visitorId, @PathVariable Long waitingRoomId) {
        VisitDTO updatedVisit = visitService.cancelVisit(visitorId,waitingRoomId);
        return ResponseEntity.ok(updatedVisit);
    }
}

