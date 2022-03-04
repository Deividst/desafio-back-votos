package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.SessionResponseDto;
import com.github.deividst.votos.dtos.SessionSaveRequestDto;
import com.github.deividst.votos.model.Record;
import com.github.deividst.votos.model.Session;
import com.github.deividst.votos.repository.RecordRepository;
import com.github.deividst.votos.repository.SessionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SessionServiceTest {

    @InjectMocks
    private SessionService sessionService;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private RecordRepository recordRepository;


    @Test
    void givenSaveSession_shouldReturnSuccess() {
        when(recordRepository.findById(any())).thenReturn(Optional.of(Record.builder().id(1L).build()));
        when(sessionRepository.save(any(Session.class))).thenReturn(buildSession());

        SessionResponseDto session = sessionService.save(buildSessionSaveRequestDto());

        verify(recordRepository, times(1)).save(any(Record.class));
        verify(sessionRepository, times(1)).save(any(Session.class));
        Assertions.assertEquals(1L, session.getId());
    }

    @Test
    void givenSaveSession_whenRecordIdNotExist_shouldReturnEntityNotFoundException() {
        when(recordRepository.findById(any())).thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class, () ->
                sessionService.save(buildSessionSaveRequestDto())
        );
    }

    @Test
    void givenFindAll_shouldReturnSuccess(){
        when(sessionRepository.findAll()).thenReturn(Collections.singletonList(buildSession()));

        List<SessionResponseDto> result =  sessionService.findAll();

        Assertions.assertEquals(1L, result.size());
    }

    private SessionSaveRequestDto buildSessionSaveRequestDto() {
        return SessionSaveRequestDto.builder()
                .recordId(1L)
                .initialDate(LocalDateTime.of(2022, 3, 4, 12, 0, 0))
                .build();
    }

    private Session buildSession() {
        return Session.builder()
                .id(1L)
                .initialDate(LocalDateTime.of(2022, 3, 4, 12, 0, 0))
                .finalDate(LocalDateTime.of(2022, 3, 4, 12, 1, 0))
                .build();
    }

}