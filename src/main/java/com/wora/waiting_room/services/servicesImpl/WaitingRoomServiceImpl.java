package com.wora.waiting_room.services.servicesImpl;

import com.wora.waiting_room.dtos.VisitDTO.EmbeddedVisitDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomCreateDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomUpdateDTO;
import com.wora.waiting_room.entities.WaitingRoom;
import com.wora.waiting_room.entities.enums.Status;
import com.wora.waiting_room.mappers.VisitMapper;
import com.wora.waiting_room.mappers.WaitingRoomMapper;
import com.wora.waiting_room.repositories.VisitRepo;
import com.wora.waiting_room.repositories.WaitingRoomRepo;
import com.wora.waiting_room.services.servicesIntr.WaitingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor
public class WaitingRoomServiceImpl implements WaitingRoomService {

    private final WaitingRoomRepo waitingRoomRepository;
    private final WaitingRoomMapper waitingRoomMapper;
    private final VisitRepo visitRepo;
    private final VisitMapper visitMapper;

    @Override
    public WaitingRoomDTO save(WaitingRoomCreateDTO createDto) {
        WaitingRoom waitingRoom = waitingRoomMapper.toEntity(createDto);
        waitingRoom = waitingRoomRepository.save(waitingRoom);
        return waitingRoomMapper.toDto(waitingRoom);
    }

    @Override
    public WaitingRoomDTO findById(Long id) {
        WaitingRoom waitingRoom = waitingRoomRepository.findById(id).orElse(null);
        return waitingRoomMapper.toDto(waitingRoom);
    }

    @Override
    public WaitingRoomDTO update(WaitingRoomUpdateDTO updateDto, Long id) {
        WaitingRoom waitingRoom = waitingRoomRepository.findById(id).orElse(null);
        if (waitingRoom != null) {
            waitingRoomMapper.toEntity(updateDto); // Map updates to the entity
            waitingRoom = waitingRoomRepository.save(waitingRoom);
        }
        return waitingRoomMapper.toDto(waitingRoom);
    }

    @Override
    public List<WaitingRoomDTO> findAll() {
        return waitingRoomRepository.findAll().stream()
                .map(waitingRoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        waitingRoomRepository.deleteById(id);
    }

    private List<EmbeddedVisitDTO> getSortedVisitsByAlgorithm(List<EmbeddedVisitDTO> visits, String strategy) {
        List<EmbeddedVisitDTO> filteredVisits = visits.stream()
                .filter(visit -> visit.getStatus() == Status.WAITING)
                .toList();

        List<EmbeddedVisitDTO> sortedVisits = switch (strategy.toUpperCase()) {
            case "FIFO" -> filteredVisits.stream()
                    .sorted(Comparator.comparing(EmbeddedVisitDTO::getArrivalTime))
                    .collect(Collectors.toList());
            case "PRIORITY" -> filteredVisits.stream()
                    .sorted((v1, v2) -> Integer.compare(v2.getPriority(), v1.getPriority()))
                    .collect(Collectors.toList());
            case "SJF" -> filteredVisits.stream()
                    .sorted(Comparator.comparing(EmbeddedVisitDTO::getEstimatedProcessingTime))
                    .collect(Collectors.toList());
            default -> throw new IllegalArgumentException("Invalid sorting strategy: " + strategy);
        };

        return sortedVisits;
    }

}

