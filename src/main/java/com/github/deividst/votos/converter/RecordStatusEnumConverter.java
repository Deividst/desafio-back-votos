package com.github.deividst.votos.converter;

import com.github.deividst.votos.enums.RecordStatusEnum;

import javax.persistence.AttributeConverter;
import java.util.Objects;

public class RecordStatusEnumConverter implements AttributeConverter<RecordStatusEnum, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RecordStatusEnum recordStatusEnum) {
        return Objects.isNull(recordStatusEnum) ? null : recordStatusEnum.getCode();
    }

    @Override
    public RecordStatusEnum convertToEntityAttribute(Integer integer) {
        return Objects.isNull(integer) ? null : RecordStatusEnum.getEnum(integer);
    }
}
