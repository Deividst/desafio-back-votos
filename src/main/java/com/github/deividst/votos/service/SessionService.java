package com.github.deividst.votos.service;

import com.github.deividst.votos.dtos.SessionResponseDto;
import com.github.deividst.votos.dtos.SessionSaveRequestDto;
import com.github.deividst.votos.mapper.SessionMapper;
import com.github.deividst.votos.model.Record;
import com.github.deividst.votos.model.Session;
import com.github.deividst.votos.repository.RecordRepository;
import com.github.deividst.votos.repository.SessionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final RecordRepository recordRepository;

    public SessionService(SessionRepository sessionRepository, RecordRepository recordRepository) {
        this.sessionRepository = sessionRepository;
        this.recordRepository = recordRepository;
    }

    @Transactional
    public SessionResponseDto save(SessionSaveRequestDto sessionDto) {
        verifyFinalDate(sessionDto);
        Session sessionEntity = SessionMapper.toSaveEntity(sessionDto);
        Optional<Record> recordEntity = this.recordRepository.findById(sessionDto.getRecordId());

        if (recordEntity.isEmpty()) {
            throw new EntityNotFoundException("Nenhum registro encontrato com o recordId informado.");
        }

        sessionEntity = this.sessionRepository.save(sessionEntity);
        recordEntity.get().setSession(sessionEntity);
        this.recordRepository.save(recordEntity.get());

        return SessionMapper.toDto(sessionEntity);
    }

    public List<SessionResponseDto> findAll() {
        List<Session> sessionList = this.sessionRepository.findAll();
        return SessionMapper.toListDto(sessionList);
    }

    private void verifyFinalDate(SessionSaveRequestDto sessionDto) {
        if (Objects.isNull(sessionDto.getFinalDate())) {
            sessionDto.setFinalDate(sessionDto.getInitialDate().plusMinutes(1));
        }
    }

}
