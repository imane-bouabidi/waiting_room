package com.wora.waiting_room.services.servicesImpl;


import com.wora.waiting_room.config.VisitProperties;
import com.wora.waiting_room.dtos.VisitDTO.EmbeddedVisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitCreateDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitUpdateDTO;
import com.wora.waiting_room.entities.Visit;

import com.wora.waiting_room.entities.Visitor;
import com.wora.waiting_room.entities.WaitingRoom;
import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.entities.enums.Status;
import com.wora.waiting_room.exceptions.EntityNotFoundException;
import com.wora.waiting_room.mappers.VisitMapper;
import com.wora.waiting_room.repositories.VisitRepo;
import com.wora.waiting_room.repositories.VisitorRepo;
import com.wora.waiting_room.repositories.WaitingRoomRepo;
import com.wora.waiting_room.services.servicesIntr.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.Duration;
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

        WaitingRoom waitingRoom = waitingRoomRepo.findById(visitDTO.getWaitingRoomId()).orElseThrow(() -> new EntityNotFoundException("Visitor not found"));;
        Visitor visitor = visitorRepo.findById(visitDTO.getVisitorId()).orElseThrow(() -> new EntityNotFoundException("waiting room not found"));;
        Visit visit = visitMapper.toEntity(visitDTO);
        VisitEmbeddedId id = new VisitEmbeddedId(visitDTO.getVisitorId(), visitDTO.getWaitingRoomId());
        visit.setId(id);
        visit.setVisitor(visitor);
        visit.setWaitingRoom(waitingRoom);
        visit.setArrivalTime(visitDTO.getArrivalTime());
        if(visitDTO.getPriority() == null){
        visit.setPriority(visitProperties.getDefaultPriority());
        }
        if(visitDTO.getEstimatedProcessingTime() == null){
        visit.setEstimatedProcessingTime(visitProperties.getDefaultEstimatedTime());
        }
        if(visitDTO.getStatus() == null){
        visit.setStatus(visitProperties.getDefaultVisitorStatus());
        }
        Visit savedVisit = visitRepository.save(visit);
        return visitMapper.toDto(savedVisit);
    }

    public VisitDTO inProgress(Long visitorId,Long waitingRoomId) {
        VisitEmbeddedId id = new VisitEmbeddedId(visitorId, waitingRoomId);
        Optional<Visit> visitOptional = visitRepository.findById(id);
        if (visitOptional.isPresent()) {
            Visit visit = visitOptional.get();

            visit.setStatus(Status.IN_PROGRESS);
            visit.setStartTime(LocalDateTime.now());

            visit = visitRepository.save(visit);

            return visitMapper.toDto(visit);
        }

        throw new EntityNotFoundException("Visit not found");
    }

    public VisitDTO finishVisit(Long visitorId,Long waitingRoomId) {
        VisitEmbeddedId id = new VisitEmbeddedId(visitorId, waitingRoomId);
        Optional<Visit> visitOptional = visitRepository.findById(id);
        if (visitOptional.isPresent()) {
            Visit visit = visitOptional.get();

            visit.setStatus(Status.FINISHED);
            visit.setEndTime(LocalDateTime.now());

            visit = visitRepository.save(visit);

            return visitMapper.toDto(visit);
        }

        throw new EntityNotFoundException("Visit not found");
    }

    public VisitDTO cancelVisit(Long visitorId,Long waitingRoomId) {
        VisitEmbeddedId id = new VisitEmbeddedId(visitorId, waitingRoomId);
        Optional<Visit> visitOptional = visitRepository.findById(id);
        if (visitOptional.isPresent()) {
            Visit visit = visitOptional.get();

            visit.setStatus(Status.CANCELLED);

            visit = visitRepository.save(visit);

            return visitMapper.toDto(visit);
        }

        throw new EntityNotFoundException("Visit not found");
    }

    @Override
    public VisitDTO findById(VisitEmbeddedId id) {
        Visit visit = visitRepository.findById(id).orElse(null);
        return visitMapper.toDto(visit);
    }

    public List<Visit> findByWaitingRoomId(Long id) {
        return visitRepository.findByWaitingRoomId(id);
    }

    @Override
    public VisitDTO update(VisitUpdateDTO updateDto, VisitEmbeddedId id) {
        Visit visit = visitRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Visit not found") );
        if (visit != null) {
            if (updateDto.getArrivalTime() != null) {
                visit.setArrivalTime(updateDto.getArrivalTime());
            }
            if (updateDto.getStartTime() != null) {
                visit.setStartTime(updateDto.getStartTime());
            }
            if (updateDto.getPriority() != null) {
                visit.setPriority(updateDto.getPriority());
            }
            if (updateDto.getStatus() != null) {
                if (updateDto.getStartTime() != null) {
                    visit.setStatus(Status.IN_PROGRESS);
                }
                else if (updateDto.getEndTime() != null) {
                    visit.setStatus(Status.FINISHED);
                }else visit.setStatus(updateDto.getStatus());
            }
            if (updateDto.getEndTime() != null) {
                visit.setEndTime(updateDto.getEndTime());
            }
            if (updateDto.getEstimatedProcessingTime() != null) {
                visit.setEstimatedProcessingTime(updateDto.getEstimatedProcessingTime());
            }
            visit = visitRepository.save(visit);
        }
        return visitMapper.toDto(visit);
    }

    @Override
    public List<VisitDTO> findAll(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return visitRepository.findAll(pageable).stream()
                .map(visitMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(VisitEmbeddedId id) {
        visitRepository.deleteById(id);
    }

    public Duration calculateAverageWaitTime() {
        List<Visit> visits = visitRepository.findAll().stream()
                .filter(visit -> visit.getStatus() == Status.FINISHED)
                .toList();
//        System.out.println(visits);

        Duration totalWaitTime = visits.stream()
                .filter(visit -> visit.getArrivalTime() != null && visit.getStartTime() != null)
                .map(visit -> Duration.between(
                        visit.getArrivalTime(),
                        visit.getStartTime()
                ))
                .reduce(Duration.ZERO, Duration::plus);

        return totalWaitTime;
    }

}

