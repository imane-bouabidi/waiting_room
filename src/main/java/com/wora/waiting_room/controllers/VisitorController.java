package com.wora.waiting_room.controllers;

import com.wora.waiting_room.dtos.VisitorDTO.VisitorCreateDTO;
import com.wora.waiting_room.dtos.VisitorDTO.VisitorDTO;
import com.wora.waiting_room.services.servicesIntr.VisitorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/visitors")
@RequiredArgsConstructor
public class VisitorController {

    private final VisitorService visitorService;

    @GetMapping("/{id}")
    public ResponseEntity<VisitorDTO> findById(@PathVariable Long id){
        return new ResponseEntity<>(visitorService.findById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<VisitorDTO>> findAll(
            @RequestParam int page,
            @RequestParam int size
    ){
        List<VisitorDTO> visitorList = visitorService.findAll(page, size);
        return new ResponseEntity<>(visitorList, HttpStatus.OK);
    }

    @PostMapping("/save")
    public ResponseEntity<VisitorDTO> save(@RequestBody @Valid VisitorCreateDTO createVisitorDto){
        return new ResponseEntity<>(visitorService.save(createVisitorDto), HttpStatus.CREATED);
    }
}
