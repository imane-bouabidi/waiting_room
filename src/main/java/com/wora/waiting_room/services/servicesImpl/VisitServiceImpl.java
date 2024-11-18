package com.wora.waiting_room.services.servicesImpl;


import com.wora.waiting_room.dtos.VisitDTO.VisitCreateDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitUpdateDTO;
import com.wora.waiting_room.entities.Visit;

import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.mappers.VisitMapper;
import com.wora.waiting_room.repositories.VisitRepo;
import com.wora.waiting_room.services.servicesIntr.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitServiceImpl implements VisitService {

    private final VisitRepo visitRepository;
    private final VisitMapper visitMapper;

    @Override
    public VisitDTO save(VisitCreateDTO createDto) {
        Visit visit = visitMapper.toEntity(createDto);
        visit = visitRepository.save(visit);
        return visitMapper.toDto(visit);
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
}

