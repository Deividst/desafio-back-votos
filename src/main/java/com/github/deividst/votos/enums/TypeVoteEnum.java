package com.github.deividst.votos.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum TypeVoteEnum {
    YES(1),
    NO(2);

    private final Integer code;

    public static TypeVoteEnum getEnum(Integer code) throws IllegalArgumentException {
        return Arrays.stream(TypeVoteEnum.values())
                .filter(status -> status.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
