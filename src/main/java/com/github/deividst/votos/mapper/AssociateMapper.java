package com.github.deividst.votos.mapper;

import com.github.deividst.votos.dtos.AssociateResponseDto;
import com.github.deividst.votos.dtos.AssociateSaveRequestDto;
import com.github.deividst.votos.model.Associate;

import java.time.LocalDateTime;

public class AssociateMapper {

    public static Associate toEntity(AssociateSaveRequestDto dto) {
        return Associate.builder()
                .name(dto.getName())
                .cpf(dto.getCpf())
                .registerDate(LocalDateTime.now())
                .build();
    }

    public static AssociateResponseDto toDto(Associate entity) {
        return AssociateResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .cpf(entity.getCpf())
                .registerDate(entity.getRegisterDate())
                .build();
    }

}
