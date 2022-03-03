package com.github.deividst.votos.exceptions;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

    private final String messageError;

    public BusinessException(String messageError) {
        this.messageError = messageError;
    }
}
