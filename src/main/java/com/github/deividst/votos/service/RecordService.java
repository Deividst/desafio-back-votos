package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.RecordResponseDto;
import com.github.deividst.votos.dtos.RecordSaveRequestDto;
import com.github.deividst.votos.mapper.RecordMapper;
import com.github.deividst.votos.model.Record;
import com.github.deividst.votos.repository.RecordRepository;
import org.springframework.stereotype.Service;

@Service
public class RecordService {

    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    public RecordResponseDto saveRecord(RecordSaveRequestDto recordSaveRequestDto) {
        Record recordEntity = RecordMapper.toSaveEntity(recordSaveRequestDto);
        recordEntity = this.recordRepository.save(recordEntity);
        return RecordMapper.toResponseDto(recordEntity);
    }

}
