package com.github.deividst.votos.converter;

import com.github.deividst.votos.enums.TypeVoteEnum;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class VoteTypeEnumConverter implements AttributeConverter<TypeVoteEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TypeVoteEnum typeVoteEnum) {
        return Objects.isNull(typeVoteEnum) ? null : typeVoteEnum.getCode();
    }

    @Override
    public TypeVoteEnum convertToEntityAttribute(Integer integer) {
        return Objects.isNull(integer) ? null : TypeVoteEnum.getEnum(integer);
    }
}
