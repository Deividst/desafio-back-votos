package com.github.deividst.votos.mapper;

import com.github.deividst.votos.dtos.RecordSaveRequestDto;
import com.github.deividst.votos.dtos.RecordResponseDto;
import com.github.deividst.votos.enums.RecordStatusEnum;
import com.github.deividst.votos.model.Record;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class RecordMapper {

    public static Record toSaveEntity(RecordSaveRequestDto dto) {
        return Record.builder()
                .description(dto.getDescription())
                .subject(dto.getSubject())
                .registerDate(LocalDateTime.now())
                .status(RecordStatusEnum.IN_PROGRESS)
                .session(Objects.isNull(dto.getSession()) ? null : SessionMapper.toSaveEntity(dto.getSession()))
                .build();
    }

    public static RecordResponseDto toResponseDto(Record entity) {
        return RecordResponseDto.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .subject(entity.getSubject())
                .status(entity.getStatus())
                .registerDate(entity.getRegisterDate())
                .session(Objects.isNull(entity.getSession()) ? null : SessionMapper.toDto(entity.getSession()))
                .build();
    }

    public static List<RecordResponseDto> toResponseList(List<Record> result) {
        return result.stream()
                .map(RecordMapper::toResponseDto)
                .collect(Collectors.toList());
    }

}
