package com.wora.waiting_room.services.servicesImpl;

import com.wora.waiting_room.config.WaitingRoomProperties;
import com.wora.waiting_room.dtos.VisitDTO.EmbeddedVisitDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomCreateDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomDTO;
import com.wora.waiting_room.dtos.WaitingRoomDTO.WaitingRoomUpdateDTO;
import com.wora.waiting_room.entities.Visit;
import com.wora.waiting_room.entities.WaitingRoom;
import com.wora.waiting_room.entities.enums.AlgorithmType;
import com.wora.waiting_room.entities.enums.Status;
import com.wora.waiting_room.exceptions.EntityNotFoundException;
import com.wora.waiting_room.mappers.VisitMapper;
import com.wora.waiting_room.mappers.WaitingRoomMapper;
import com.wora.waiting_room.repositories.VisitRepo;
import com.wora.waiting_room.repositories.WaitingRoomRepo;
import com.wora.waiting_room.services.servicesIntr.WaitingRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
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
    private final WaitingRoomProperties properties;

    @Override
    public WaitingRoomDTO save(WaitingRoomCreateDTO createDto) {
        if(createDto.getAlgorithmType() == null){
            createDto.setAlgorithmType(properties.getDefaultAlgorithmType());
        }
        if(createDto.getCapacity() == null){
            createDto.setCapacity(properties.getDefaultCapacity());
        }
        if(createDto.getWorkMode() == null){
            createDto.setWorkMode(properties.getDefaultWorkMode());
        }
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
        WaitingRoom waitingRoom = waitingRoomRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("WaitingRoom not found"));
        if(updateDto.getDate() != null){
            waitingRoom.setDate(updateDto.getDate());
        }
        if(updateDto.getAlgorithmType() != null){
            waitingRoom.setAlgorithmType(updateDto.getAlgorithmType());
        }
        if(updateDto.getCapacity() != null){
            waitingRoom.setCapacity(updateDto.getCapacity());
        }
        if(updateDto.getWorkMode() != null){
            waitingRoom.setWorkMode(updateDto.getWorkMode());
        }
            waitingRoom = waitingRoomRepository.save(waitingRoom);
        return waitingRoomMapper.toDto(waitingRoom);
    }

    @Override
    public List<WaitingRoomDTO> findAll(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return waitingRoomRepository.findAll(pageable).stream()
                .map(waitingRoomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        waitingRoomRepository.deleteById(id);
    }

    public List<EmbeddedVisitDTO> getSortedVisitsByAlgorithm(Long waitingRoomId) {
        WaitingRoom waitingRoom = waitingRoomRepository.findById(waitingRoomId).orElseThrow(() -> new EntityNotFoundException("WaitingRoom not found"));
        AlgorithmType strategy = waitingRoom.getAlgorithmType();
        List<Visit> visits = visitRepo.findByWaitingRoomId(waitingRoomId);
        List<EmbeddedVisitDTO> embeddedVisits = visits.stream()
                .map(visitMapper::toEmbeddedDto)
                .filter(visit -> visit.getStatus() == Status.WAITING)
                .toList();

        return switch (strategy) {
            case FIFO -> embeddedVisits.stream()
                    .sorted(Comparator.comparing(EmbeddedVisitDTO::getArrivalTime))
                    .collect(Collectors.toList());
            case PRIORITY -> embeddedVisits.stream()
                    .sorted((v1, v2) -> Byte.compare(v2.getPriority(), v1.getPriority()))
                    .collect(Collectors.toList());
            case SJF -> embeddedVisits.stream()
                    .sorted(Comparator.comparing(EmbeddedVisitDTO::getEstimatedProcessingTime))
                    .collect(Collectors.toList());
            default -> throw new IllegalArgumentException("Invalid sorting strategy: " + strategy);
        };
    }

}

