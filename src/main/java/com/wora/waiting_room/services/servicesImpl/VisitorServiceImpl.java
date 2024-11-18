package com.wora.waiting_room.services.servicesImpl;

import com.wora.waiting_room.dtos.VisitorDTO.VisitorCreateDTO;
import com.wora.waiting_room.dtos.VisitorDTO.VisitorDTO;
import com.wora.waiting_room.dtos.VisitorDTO.VisitorUpdateDTO;
import com.wora.waiting_room.entities.Visitor;
import com.wora.waiting_room.mappers.VisitorMapper;
import com.wora.waiting_room.repositories.VisitorRepo;
import com.wora.waiting_room.services.servicesIntr.VisitorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class VisitorServiceImpl implements VisitorService {

    private final VisitorRepo visitorRepository;
    private final VisitorMapper visitorMapper;

    @Override
    public VisitorDTO save(VisitorCreateDTO createDto) {
        Visitor visitor = visitorMapper.toEntity(createDto);
        visitor = visitorRepository.save(visitor);
        return visitorMapper.toDto(visitor);
    }

    @Override
    public VisitorDTO findById(Long id) {
        Visitor visitor = visitorRepository.findById(id).orElse(null);
        return visitorMapper.toDto(visitor);
    }

    @Override
    public VisitorDTO update(VisitorUpdateDTO updateDto, Long id) {
        Visitor visitor = visitorRepository.findById(id).orElse(null);
        if (visitor != null) {
            visitorMapper.toEntity(updateDto); // Map updates to the entity
            visitor = visitorRepository.save(visitor);
        }
        return visitorMapper.toDto(visitor);
    }

    @Override
    public List<VisitorDTO> findAll() {
        return visitorRepository.findAll().stream()
                .map(visitorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        visitorRepository.deleteById(id);
    }
}

