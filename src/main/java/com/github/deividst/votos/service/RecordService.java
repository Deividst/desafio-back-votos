package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.RecordResponseDto;
import com.github.deividst.votos.dtos.RecordSaveRequestDto;
import com.github.deividst.votos.mapper.RecordMapper;
import com.github.deividst.votos.model.Record;
import com.github.deividst.votos.repository.RecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public RecordResponseDto save(RecordSaveRequestDto recordDto) {
        verifySessionFinalDate(recordDto);
        Record recordEntity = RecordMapper.toSaveEntity(recordDto);
        recordEntity = this.recordRepository.save(recordEntity);
        return RecordMapper.toResponseDto(recordEntity);
    }

    public List<RecordResponseDto> findAll() {
        List<Record> result = this.recordRepository.findAll();
        return RecordMapper.toResponseList(result);
    }

    private void verifySessionFinalDate(RecordSaveRequestDto recordDto) {
        if (Objects.nonNull(recordDto.getSession()) && Objects.isNull(recordDto.getSession().getFinalDate())) {
            recordDto.getSession().setFinalDate(recordDto.getSession().getInitialDate().plusMinutes(1));
        }
    }

}
