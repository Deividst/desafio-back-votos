package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.RecordResponseDto;
import com.github.deividst.votos.dtos.RecordSaveRequestDto;
import com.github.deividst.votos.dtos.SessionResponseDto;
import com.github.deividst.votos.enums.RecordStatusEnum;
import com.github.deividst.votos.model.Record;
import com.github.deividst.votos.repository.RecordRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecordServiceTest {

    @InjectMocks
    private RecordService recordService;

    @Mock
    private RecordRepository recordRepository;

    @Test
    void givenSaveRecord_shouldReturnSuccess(){
        when(recordRepository.save(any(Record.class))).thenReturn(buildRecord());

        RecordResponseDto responseDto = recordService.save(buildRecordSaveRequestDto());

        verify(recordRepository, times(1)).save(any(Record.class));
        Assertions.assertEquals(1L, responseDto.getId());
    }

    @Test
    void givenFindAll_shouldReturnSuccess(){
        when(recordRepository.findAll()).thenReturn(Collections.singletonList((buildRecord())));

        List<RecordResponseDto> result =  recordService.findAll();

        Assertions.assertEquals(1L, result.size());
    }

    private RecordSaveRequestDto buildRecordSaveRequestDto() {
        return RecordSaveRequestDto.builder()
                .subject("test")
                .description("description")
                .build();
    }

    private Record buildRecord(){
        return Record.builder()
                .id(1L)
                .status(RecordStatusEnum.IN_PROGRESS)
                .description("test")
                .subject("test")
                .registerDate(LocalDateTime.now())
                .build();
    }
}