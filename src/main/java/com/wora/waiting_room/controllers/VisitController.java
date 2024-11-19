package com.wora.waiting_room.controllers;

import com.wora.waiting_room.dtos.VisitDTO.VisitCreateDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitUpdateDTO;
import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.services.servicesImpl.VisitServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/visits")
@RequiredArgsConstructor
public class VisitController {

    private final VisitServiceImpl visitService;

//    @GetMapping("/sorted")
//    public ResponseEntity<Page<VisitDTO>> getSortedVisits(
//            @RequestParam String strategy,
//            @RequestParam(defaultValue = "0") int page,
//            @RequestParam(defaultValue = "10") int size) {
//
//        if (!isValidStrategy(strategy)) {
//            return ResponseEntity.badRequest().body(null);
//        }
//
//        Pageable pageable = PageRequest.of(page, size);
//
//        Page<VisitDTO> visitsPage = visitService.getSortedVisitsWithPagination(strategy, pageable);
//
//        return ResponseEntity.ok(visitsPage);
//    }


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

    @DeleteMapping("/{visitorId}/{waitingRoomId}")
    public ResponseEntity<Void> deleteVisit(
            @PathVariable("visitorId") Long visitorId,
            @PathVariable("waitingRoomId") Long waitingRoomId) {

        VisitEmbeddedId id = new VisitEmbeddedId(visitorId, waitingRoomId);
        visitService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/in-progress")
    public ResponseEntity<VisitDTO> startVisit(@RequestBody @Valid VisitDTO visitDTO) {
        VisitDTO updatedVisit = visitService.inProgress(visitDTO);
        return ResponseEntity.ok(updatedVisit);
    }

    @PostMapping("/finish")
    public ResponseEntity<VisitDTO> finishVisit(@RequestBody @Valid VisitDTO visitDTO) {
        VisitDTO updatedVisit = visitService.finishVisit(visitDTO);
        return ResponseEntity.ok(updatedVisit);
    }

    @PostMapping("/cancel")
    public ResponseEntity<VisitDTO> cancelVisit(@RequestBody @Valid VisitDTO visitDTO) {
        VisitDTO updatedVisit = visitService.cancelVisit(visitDTO);
        return ResponseEntity.ok(updatedVisit);
    }
}

