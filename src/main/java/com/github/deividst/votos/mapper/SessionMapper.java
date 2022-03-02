package com.github.deividst.votos.mapper;

import com.github.deividst.votos.dtos.SessionDto;
import com.github.deividst.votos.model.Session;

public class SessionMapper {

    public static Session toSaveEntity(SessionDto dto) {
        return Session.builder()
                .initialDate(dto.getInitialDate())
                .finalDate(dto.getFinalDate())
                .build();
    }

    public static SessionDto toDto(Session entity) {
        return SessionDto.builder()
                .id(entity.getId())
                .finalDate(entity.getFinalDate())
                .initialDate(entity.getInitialDate())
                .build();
    }
}
