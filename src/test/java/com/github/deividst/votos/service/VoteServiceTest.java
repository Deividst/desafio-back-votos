package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.VoteDto;
import com.github.deividst.votos.enums.TypeVoteEnum;
import com.github.deividst.votos.exceptions.BusinessException;
import com.github.deividst.votos.model.Associate;
import com.github.deividst.votos.model.Record;
import com.github.deividst.votos.model.Session;
import com.github.deividst.votos.model.Vote;
import com.github.deividst.votos.repository.AssociateRepository;
import com.github.deividst.votos.repository.RecordRepository;
import com.github.deividst.votos.repository.VoteRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VoteServiceTest {

    @InjectMocks
    private VoteService voteService;

    @Mock
    private AssociateRepository associateRepository;

    @Mock
    private  VoteRepository voteRepository;

    @Mock
    private  RecordRepository recordRepository;

    @Mock
    private  CPFIntegrationService cpfIntegrationService;

    @Test
    void givenProcessVote_shouldReturnSuccess(){
        when(associateRepository.findById(any())).thenReturn(Optional.of(Associate.builder().id(1L).build()));

        when(recordRepository.findById(any())).thenReturn(Optional.of(Record.builder()
                .session(Session.builder().finalDate(LocalDateTime.now().plusHours(1L)).build())
                .id(1L)
                .build()));

        voteService.processVote(buildVoteDto());

        verify(cpfIntegrationService, times(1)).checkCPF(any());
        verify(voteRepository, times(1)).save(any(Vote.class));
    }

    @Test
    void givenProcessVote_whenAssociateNotExist_shouldReturnEntityNotFoundException(){
        when(associateRepository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () ->
                voteService.processVote(buildVoteDto())
        );
    }

    @Test
    void givenProcessVote_whenRecordNotExist_shouldReturnEntityNotFoundException(){
        when(associateRepository.findById(any())).thenReturn(Optional.of(Associate.builder().id(1L).build()));

        when(recordRepository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () ->
                voteService.processVote(buildVoteDto())
        );
    }

    @Test
    void givenProcessVote_whenSessionClosed_shouldReturnBusinessException(){
        when(associateRepository.findById(any())).thenReturn(Optional.of(Associate.builder().id(1L).build()));

        when(recordRepository.findById(any())).thenReturn(Optional.of(Record.builder()
                .session(Session.builder().finalDate(LocalDateTime.now().minusHours(1L)).build())
                .id(1L)
                .build()));

        Assertions.assertThrows(BusinessException.class, () ->
                voteService.processVote(buildVoteDto())
        );
    }

    @Test
    void givenProcessVote_whenAssociateAlreadyVoted_shouldReturnBusinessException(){
        when(associateRepository.findById(any())).thenReturn(Optional.of(Associate.builder().id(1L).build()));

        when(recordRepository.findById(any())).thenReturn(Optional.of(Record.builder()
                .session(Session.builder().finalDate(LocalDateTime.now().plusHours(1L)).build())
                .id(1L)
                .build()));

        when(voteRepository.save(any())).thenThrow(DataIntegrityViolationException.class);

        Assertions.assertThrows(BusinessException.class, () ->
                voteService.processVote(buildVoteDto())
        );
    }

    @Test
    void givenProcessVote_whenAssociateUnableToVote_shouldReturnBusinessException(){
        when(associateRepository.findById(any())).thenReturn(Optional.of(Associate.builder().id(1L).build()));

        doThrow(BusinessException.class).when(cpfIntegrationService).checkCPF(any());

        Assertions.assertThrows(BusinessException.class, () ->
                voteService.processVote(buildVoteDto())
        );
    }

    private VoteDto buildVoteDto() {
        return VoteDto.builder()
                .associateId(1L)
                .recordId(1L)
                .voteType(TypeVoteEnum.YES)
                .build();
    }

}