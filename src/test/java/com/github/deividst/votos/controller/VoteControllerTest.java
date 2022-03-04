package com.github.deividst.votos.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.deividst.votos.dtos.VoteDto;
import com.github.deividst.votos.enums.TypeVoteEnum;
import com.github.deividst.votos.exceptions.BusinessException;
import com.github.deividst.votos.service.VoteService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@WebMvcTest(VoteController.class)
class VoteControllerTest {

    private static final String URL = "http://localhost:8081/v1/vote";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VoteService voteService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void givenProcessVoteRequested_shouldReturnOk() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(objectMapper.writeValueAsString(buildVoteDto()))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.OK.value()));

        verify(voteService, times(1))
                .processVote(any(VoteDto.class));
    }

    @Test
    void givenProcessVoteRequested_whenRecordIdisNull_shouldReturnBadRequest() throws Exception {
        VoteDto voteDto = buildVoteDto();
        voteDto.setRecordId(null);

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(objectMapper.writeValueAsString(voteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]").value("O campo recordId é obrigatório"));
    }

    @Test
    void givenProcessVoteRequested_whenAssociateisNull_shouldReturnBadRequest() throws Exception {
        VoteDto voteDto = buildVoteDto();
        voteDto.setAssociateId(null);

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(objectMapper.writeValueAsString(voteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]").value("O campo associateId é obrigatório"));
    }

    @Test
    void givenProcessVoteRequested_whenVoteTypeIsNull_shouldReturnBadRequest() throws Exception {
        VoteDto voteDto = buildVoteDto();
        voteDto.setVoteType(null);

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .content(objectMapper.writeValueAsString(voteDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]").value("O campo voteType é obrigatório"));
    }

    @Test
    void givenSaveRequested_whenRecordIdNotExist_shouldReturnNotFound() throws Exception {
        doThrow(new EntityNotFoundException("Nenhum registro encontrato com o recordId informado."))
                .when(voteService).processVote(any(VoteDto.class));

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buildVoteDto())))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.errorMessages[0]")
                        .value("Nenhum registro encontrato com o recordId informado."));
    }

    @Test
    void givenSaveRequested_whenAssociateIdNotExist_shouldReturnNotFound() throws Exception {
        doThrow(new EntityNotFoundException("Nenhum registro encontrato com o associateId informado."))
                .when(voteService).processVote(any(VoteDto.class));

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buildVoteDto())))
                .andExpect(status().is(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.errorMessages[0]")
                        .value("Nenhum registro encontrato com o associateId informado."));
    }

    @Test
    void givenSaveRequested_whenAssociateAlreadyVoted_shouldReturnBadRequest() throws Exception {
        doThrow(new BusinessException("O associado não pode votar duas vezes na mesma sessão."))
                .when(voteService).processVote(any(VoteDto.class));

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buildVoteDto())))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]")
                        .value("O associado não pode votar duas vezes na mesma sessão."));
    }

    @Test
    void givenSaveRequested_whenSessionIsClosed_shouldReturnBadRequest() throws Exception {
        doThrow(new BusinessException("A sessão informada não está disponivel"))
                .when(voteService).processVote(any(VoteDto.class));

        mockMvc.perform(MockMvcRequestBuilders
                .post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(buildVoteDto())))
                .andExpect(status().is(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.errorMessages[0]")
                        .value("A sessão informada não está disponivel"));
    }

    private VoteDto buildVoteDto() {
        return VoteDto.builder()
                .associateId(1L)
                .recordId(1L)
                .voteType(TypeVoteEnum.YES)
                .build();
    }

}