package com.github.deividst.votos.mapper;

import com.github.deividst.votos.dtos.SessionResponseDto;
import com.github.deividst.votos.dtos.SessionSaveRecordRequestDto;
import com.github.deividst.votos.dtos.SessionSaveRequestDto;
import com.github.deividst.votos.model.Session;

import java.util.List;
import java.util.stream.Collectors;

public class SessionMapper {

    public static Session toSaveEntity(SessionSaveRecordRequestDto dto) {
        return Session.builder()
                .initialDate(dto.getInitialDate())
                .finalDate(dto.getFinalDate())
                .build();
    }

    public static Session toSaveEntity(SessionSaveRequestDto dto) {
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

    public static List<SessionResponseDto> toListDto(List<Session> result){
        return result.stream()
                .map(SessionMapper::toDto)
                .collect(Collectors.toList());
    }
}
