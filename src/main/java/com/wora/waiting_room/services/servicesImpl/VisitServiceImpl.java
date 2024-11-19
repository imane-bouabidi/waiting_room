package com.wora.waiting_room.services.servicesImpl;


import com.wora.waiting_room.config.VisitProperties;
import com.wora.waiting_room.dtos.VisitDTO.VisitCreateDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitUpdateDTO;
import com.wora.waiting_room.entities.Visit;

import com.wora.waiting_room.entities.Visitor;
import com.wora.waiting_room.entities.WaitingRoom;
import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.entities.enums.Status;
import com.wora.waiting_room.mappers.VisitMapper;
import com.wora.waiting_room.repositories.VisitRepo;
import com.wora.waiting_room.repositories.VisitorRepo;
import com.wora.waiting_room.repositories.WaitingRoomRepo;
import com.wora.waiting_room.services.servicesIntr.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepo visitRepository;
    private final VisitMapper visitMapper;
    private final WaitingRoomRepo waitingRoomRepo;
    private final VisitorRepo visitorRepo;
    private final VisitProperties visitProperties;

    @Override
    public VisitDTO save(VisitCreateDTO visitDTO) {

        WaitingRoom waitingRoom = waitingRoomRepo.findById(visitDTO.getEmbeddedWaitingRoomDTO().getId()).orElse(null);
        Visitor visitor = visitorRepo.findById(visitDTO.getEmbeddedVisitorDTO().getId()).orElse(null);
        Visit visit = visitMapper.toEntity(visitDTO);
        VisitEmbeddedId id = new VisitEmbeddedId(visitDTO.getEmbeddedVisitorDTO().getId(), visitDTO.getEmbeddedWaitingRoomDTO().getId());
        visit.setId(id);
        visit.setVisitor(visitor);
        visit.setWaitingRoom(waitingRoom);
        visit.setArrivalTime(visitDTO.getArrivalTime());
        visit.setPriority(visitProperties.getDefaultPriority());
        visit.setEstimatedProcessingTime(visitProperties.getDefaultEstimatedTime());
        visit.setStatus(visitProperties.getDefaultVisitorStatus());
        Visit savedVisit = visitRepository.save(visit);
        return visitMapper.toDto(savedVisit);
    }

    public VisitDTO inProgress(VisitDTO visitDTO) {
        Optional<Visit> visitOptional = visitRepository.findById(visitDTO.getId());
        if (visitOptional.isPresent()) {
            Visit visit = visitOptional.get();

            visit.setStatus(Status.IN_PROGRESS);
            visit.setStartTime(LocalDateTime.now());

            visit = visitRepository.save(visit);

            return visitMapper.toDto(visit);
        }

        throw new ResourceNotFoundException("Visit not found");
    }

    public VisitDTO finishVisit(VisitDTO visitDTO) {
        Optional<Visit> visitOptional = visitRepository.findById(visitDTO.getId());
        if (visitOptional.isPresent()) {
            Visit visit = visitOptional.get();

            visit.setStatus(Status.FINISHED);
            visit.setEndTime(LocalDateTime.now());

            visit = visitRepository.save(visit);

            return visitMapper.toDto(visit);
        }

        throw new ResourceNotFoundException("Visit not found");
    }

    public VisitDTO cancelVisit(VisitDTO visitDTO) {
        Optional<Visit> visitOptional = visitRepository.findById(visitDTO.getId());
        if (visitOptional.isPresent()) {
            Visit visit = visitOptional.get();

            visit.setStatus(Status.CANCELLED);

            visit = visitRepository.save(visit);

            return visitMapper.toDto(visit);
        }

        throw new ResourceNotFoundException("Visit not found");
    }

    @Override
    public VisitDTO findById(VisitEmbeddedId id) {
        Visit visit = visitRepository.findById(id).orElse(null);
        return visitMapper.toDto(visit);
    }

    @Override
    public VisitDTO update(VisitUpdateDTO updateDto, VisitEmbeddedId id) {
        Visit visit = visitRepository.findById(id).orElse(null);
        if (visit != null) {
            visitMapper.toEntity(updateDto);
            visit = visitRepository.save(visit);
        }
        return visitMapper.toDto(visit);
    }

    @Override
    public List<VisitDTO> findAll() {
        return visitRepository.findAll().stream()
                .map(visitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(VisitEmbeddedId id) {
        visitRepository.deleteById(id);
    }

    public List<VisitDTO> getVisitsByFIFO() {
        List<Visit> visits = visitRepository.findAll();
        visits.sort(Comparator.comparing(Visit::getArrivalTime));
        return visits.stream().map(visitMapper::toDto).collect(Collectors.toList());
    }

    public List<VisitDTO> getVisitsByPriority() {
        List<Visit> visits = visitRepository.findAll();
        visits.sort(Comparator.comparing(Visit::getPriority));
        return visits.stream().map(visitMapper::toDto).collect(Collectors.toList());
    }

    public List<VisitDTO> getVisitsBySJF() {
        List<Visit> visits = visitRepository.findAll();
        visits.sort(Comparator.comparing(Visit::getEstimatedProcessingTime));
        return visits.stream().map(visitMapper::toDto).collect(Collectors.toList());
    }

}

