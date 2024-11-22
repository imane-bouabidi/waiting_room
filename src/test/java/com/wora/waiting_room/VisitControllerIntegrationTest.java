package com.wora.waiting_room;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wora.waiting_room.dtos.VisitDTO.VisitCreateDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitDTO;
import com.wora.waiting_room.dtos.VisitDTO.VisitUpdateDTO;
import com.wora.waiting_room.entities.embedded.VisitEmbeddedId;
import com.wora.waiting_room.entities.enums.Status;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class VisitControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateVisit() throws Exception {
        VisitCreateDTO visitCreateDTO = new VisitCreateDTO();
        visitCreateDTO.setVisitorId(1L);
        visitCreateDTO.setWaitingRoomId(2L);
        visitCreateDTO.setArrivalTime(LocalDateTime.now());

        mockMvc.perform(post("/waiting_room/api/visits")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visitCreateDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.visitorId").value(1L))
                .andExpect(jsonPath("$.waitingRoomId").value(2L));
    }

    @Test
    void testUpdateVisit() throws Exception {
        VisitUpdateDTO visitUpdateDTO = new VisitUpdateDTO();
        visitUpdateDTO.setStatus(Status.IN_PROGRESS);

        mockMvc.perform(put("/waiting_room/api/visits/{visitorId}/{waitingRoomId}", 1L, 2L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visitUpdateDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    void testStartVisit() throws Exception {
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setId(new VisitEmbeddedId(1L, 2L));
        visitDTO.setStatus(Status.WAITING);

        mockMvc.perform(post("/waiting_room/api/visits/in-progress")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visitDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"));
    }

    @Test
    void testFinishVisit() throws Exception {
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setId(new VisitEmbeddedId(1L, 2L));
        visitDTO.setStatus(Status.IN_PROGRESS);

        mockMvc.perform(post("/waiting_room/api/visits/finish")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visitDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("FINISHED"));
    }

    @Test
    void testCancelVisit() throws Exception {
        VisitDTO visitDTO = new VisitDTO();
        visitDTO.setId(new VisitEmbeddedId(1L, 2L));
        visitDTO.setStatus(Status.IN_PROGRESS);

        mockMvc.perform(post("/waiting_room/api/visits/cancel")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(visitDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("CANCELLED"));
    }
}
