package com.github.deividst.votos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum RecordStatusEnum {
    IN_PROGRESS(1),
    APPROVED(2),
    DISAPPROVED(3);

    private final Integer code;

    public static RecordStatusEnum getEnum(Integer code) throws IllegalArgumentException {
       return Arrays.stream(RecordStatusEnum.values())
                .filter(status -> status.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
