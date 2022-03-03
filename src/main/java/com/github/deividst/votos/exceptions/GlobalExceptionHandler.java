package com.github.deividst.votos.exceptions;


import com.github.deividst.votos.dtos.ErrorDataDto;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


@ControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @ApiResponse(responseCode = "400", description = "Erro de validação nos campos do payload")
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorDataDto> processBusinessException(final MethodArgumentNotValidException ex) {
        ErrorDataDto errorDataDto = this.createErrorList(ex.getBindingResult());
        return new ResponseEntity<>(errorDataDto, HttpStatus.BAD_REQUEST);
    }

    @ApiResponse(responseCode = "400", description = "Erro na formatação do payload")
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<ErrorDataDto> processBusinessException(final HttpMessageNotReadableException ex) {

        return new ResponseEntity<>(ErrorDataDto.builder()
                .errorMessages(Collections.singletonList("O Payload informado é inválido"))
                .build(), HttpStatus.BAD_REQUEST);
    }

    private ErrorDataDto createErrorList(BindingResult bindingResult) {
        List<String> errorList = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(f -> {
            String message = messageSource.getMessage(f, LocaleContextHolder.getLocale());
            errorList.add(message);
        });

        return ErrorDataDto.builder()
                .errorMessages(errorList)
                .build();
    }

}
