package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.RecordDto;
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

    public RecordDto saveRecord(RecordDto recordDto) {
        Record recordEntity = RecordMapper.toSaveEntity(recordDto);
        recordEntity = this.recordRepository.save(recordEntity);
        return RecordMapper.toDto(recordEntity);
    }

}
