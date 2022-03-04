package com.github.deividst.votos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.deividst.votos.dtos.AssociateResponseDto;
import com.github.deividst.votos.dtos.AssociateSaveRequestDto;
import com.github.deividst.votos.dtos.SessionResponseDto;
import com.github.deividst.votos.dtos.SessionSaveRequestDto;
import com.github.deividst.votos.service.SessionService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(SessionController.class)
class SessionControllerTest {

    private static final String URL = "http://localhost:8081/v1/session";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SessionService sessionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenSaveRequested_shouldReturnCreated() throws Exception {

        when(sessionService.save(any(SessionSaveRequestDto.class))).thenReturn(buildSessionResponseDto());

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(objectMapper.writeValueAsString(buildSessionSaveRequestDto()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.initialDate").value("04-03-2022 12:00:00"))
                .andExpect(jsonPath("$.id").value(1));

        verify(sessionService, times(1))
                .save(any(SessionSaveRequestDto.class));
    }

    @Test
    void givenSaveRequested_whenInitialDateisEmpty_shouldReturnBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(SessionSaveRequestDto.builder()
                        .recordId(1L)
                        .build())))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]").value("O campo initialDate é obrigatório"));
    }

    @Test
    void givenSaveRequested_whenRecordIdisNull_shouldReturnBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(SessionSaveRequestDto.builder()
                        .initialDate(LocalDateTime.of(2022, 3,4,12,0,0))
                        .build())))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]").value("O campo recordId é obrigatório"));
    }

    @Test
    void givenSaveRequested_shouldReturnNotFound() throws Exception {
        doThrow(new EntityNotFoundException("Nenhum registro encontrato com o recordId informado."))
                .when(sessionService).save(any(SessionSaveRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buildSessionSaveRequestDto())))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.errorMessages[0]")
                        .value("Nenhum registro encontrato com o recordId informado."));
    }

    @Test
    void givenSaveRequested_shouldReturnInternalServerError() throws Exception {

        doThrow(RuntimeException.class).when(sessionService).save(any(SessionSaveRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buildSessionSaveRequestDto())))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    void givenfindAllRequested_shouldReturnOk() throws Exception {
        when(sessionService.findAll()).thenReturn(Collections.singletonList(buildSessionResponseDto()));

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.[0].initialDate").value("04-03-2022 12:00:00"))
                .andExpect(jsonPath("$.[0].id").value(1));

        verify(sessionService, times(1)).findAll();
    }

    private SessionSaveRequestDto buildSessionSaveRequestDto(){
        return SessionSaveRequestDto.builder()
                .recordId(1L)
                .initialDate(LocalDateTime.of(2022, 3,4,12,0,0))
                .build();
    }

    private SessionResponseDto buildSessionResponseDto(){
        return SessionResponseDto.builder()
                .id(1L)
                .initialDate(LocalDateTime.of(2022, 3,4,12,0,0))
                .build();
    }

}