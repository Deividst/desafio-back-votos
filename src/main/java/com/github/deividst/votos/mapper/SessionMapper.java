package com.github.deividst.votos.mapper;

import com.github.deividst.votos.dtos.SessionResponseDto;
import com.github.deividst.votos.dtos.SessionSaveRecordRequestDto;
import com.github.deividst.votos.model.Session;

public class SessionMapper {

    public static Session toSaveEntity(SessionSaveRecordRequestDto dto) {
        return Session.builder()
                .initialDate(dto.getInitialDate())
                .finalDate(dto.getFinalDate())
                .build();
    }

    public static SessionResponseDto toDto(Session entity) {
        return SessionResponseDto.builder()
                .id(entity.getId())
                .finalDate(entity.getFinalDate())
                .initialDate(entity.getInitialDate())
                .build();
    }
}
