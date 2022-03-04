package com.github.deividst.votos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.deividst.votos.dtos.AssociateResponseDto;
import com.github.deividst.votos.dtos.AssociateSaveRequestDto;
import com.github.deividst.votos.service.AssociateService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.persistence.EntityNotFoundException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(AssociateController.class)
class AssociateControllerTest {

    private static final String URL = "http://localhost:8081/v1/associates";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AssociateService associateService;

    @Autowired
    private ObjectMapper objectMapper;


    @Test
    void givenSaveRequested_shouldReturnCreated() throws Exception {

        when(associateService.save(any(AssociateSaveRequestDto.class))).thenReturn(buildAssociateResponseDto());

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(objectMapper.writeValueAsString(buildAssociateSaveRequestDto()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.CREATED.value()))
                .andExpect(jsonPath("$.cpf").value("36572935078"))
                .andExpect(jsonPath("$.name").value("teste"))
                .andExpect(jsonPath("$.id").value(1));

        verify(associateService, times(1))
                .save(any(AssociateSaveRequestDto.class));
    }

    @Test
    void givenSaveRequested_whenCpfIsEmpty_shouldReturnBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(AssociateSaveRequestDto.builder()
                        .name("teste")
                        .build())))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]").value("O campo cpf é obrigatório"));
    }

    @Test
    void givenSaveRequested_whenNameIsEmpty_shouldReturnBadRequest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(AssociateSaveRequestDto.builder()
                        .cpf("36572935078")
                        .build())))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]").value("O campo name é obrigatório"));
    }

    @Test
    void givenSaveRequested_shouldReturnInternalServerError() throws Exception {

        doThrow(RuntimeException.class).when(associateService).save(any(AssociateSaveRequestDto.class));

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buildAssociateSaveRequestDto())))
                .andExpect(status().is(HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    @Test
    void givenfindByCpfRequested_shouldReturnOk() throws Exception {
        AssociateResponseDto responseDto = buildAssociateResponseDto();
        when(associateService.findByCpf(any())).thenReturn(responseDto);

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "/" + responseDto.getCpf())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(jsonPath("$.cpf").value("36572935078"))
                .andExpect(jsonPath("$.name").value("teste"))
                .andExpect(jsonPath("$.id").value(1));

        verify(associateService, times(1))
                .findByCpf(any());
    }

    @Test
    void givenfindByCpfRequested_shouldReturnNotFound() throws Exception {
        doThrow(new EntityNotFoundException("Nenhum registro encontrato com o cpf informado."))
                .when(associateService).findByCpf(any());

        mockMvc.perform(MockMvcRequestBuilders
                .get(URL + "/1" )
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.errorMessages[0]")
                        .value("Nenhum registro encontrato com o cpf informado."));

        verify(associateService, times(1))
                .findByCpf(any());
    }

    private AssociateSaveRequestDto buildAssociateSaveRequestDto() {
        return AssociateSaveRequestDto.builder()
                .cpf("36572935078")
                .name("teste")
                .build();
    }

    private AssociateResponseDto buildAssociateResponseDto() {
        return AssociateResponseDto.builder()
                .id(1L)
                .cpf("36572935078")
                .name("teste")
                .build();
    }
}