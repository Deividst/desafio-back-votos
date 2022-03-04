package com.github.deividst.votos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.deividst.votos.dtos.RecordResponseDto;
import com.github.deividst.votos.dtos.RecordSaveRequestDto;
import com.github.deividst.votos.enums.RecordStatusEnum;
import com.github.deividst.votos.service.RecordService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(RecordController.class)
class RecordControllerTest {

    private static final String URL = "http://localhost:8081/v1/records";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecordService recordService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenSaveRequested_shouldReturnCreated() throws Exception {

        when(recordService.save(any(RecordSaveRequestDto.class))).thenReturn(buildRecordResponseDto());

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(objectMapper.writeValueAsString(buildRecordSaveRequestDto()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.status").value("IN_PROGRESS"))
                .andExpect(jsonPath("$.description").value("description"))
                .andExpect(jsonPath("$.subject").value("test"))
                .andExpect(jsonPath("$.id").value(1));

        verify(recordService, times(1))
                .save(any(RecordSaveRequestDto.class));
    }

    @Test
    void givenSaveRequested_whenDescriptionIsEmpty_shouldReturnBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RecordSaveRequestDto.builder()
                        .subject("test")
                        .build())))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]").value("O campo description é obrigatório"));
    }

    @Test
    void givenSaveRequested_whenSubjectIsEmpty_shouldReturnBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(RecordSaveRequestDto.builder()
                        .description("description")
                        .build())))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]").value("O campo subject é obrigatório"));
    }

    @Test
    void givenSaveRequested_shouldReturnInternalServerError() throws Exception {

        doThrow(RuntimeException.class).when(recordService).save(any(RecordSaveRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buildRecordSaveRequestDto())))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    void givenfindAllRequested_shouldReturnOk() throws Exception {
        when(recordService.findAll()).thenReturn(Collections.singletonList(buildRecordResponseDto()));

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.[0].status").value("IN_PROGRESS"))
                .andExpect(jsonPath("$.[0].description").value("description"))
                .andExpect(jsonPath("$.[0].subject").value("test"))
                .andExpect(jsonPath("$.[0].id").value(1));

        verify(recordService, times(1)).findAll();
    }

    private RecordSaveRequestDto buildRecordSaveRequestDto() {
        return RecordSaveRequestDto.builder()
                .subject("test")
                .description("description")
                .build();
    }

    private RecordResponseDto buildRecordResponseDto() {
        return RecordResponseDto.builder()
                .id(1L)
                .status(RecordStatusEnum.IN_PROGRESS)
                .subject("test")
                .description("description")
                .build();
    }

}