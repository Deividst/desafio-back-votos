package com.github.deividst.votos.controller;

import com.github.deividst.votos.dtos.ErrorDataDto;
import com.github.deividst.votos.dtos.RecordResponseDto;
import com.github.deividst.votos.dtos.RecordSaveRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;


public interface RecordController {

    @Operation(description = "Realiza o cadastro de uma nova Pauta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso ao cadastrar pauta.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = RecordResponseDto.class))),

            @ApiResponse(responseCode = "400", description = "Erro no payload ao cadastrar pauta.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDataDto.class))),

            @ApiResponse(responseCode = "500", description = "Resposta de erro de condição inesperada na aplicação que o impediu de atender à solicitação.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDataDto.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<RecordResponseDto> save(@Validated @RequestBody RecordSaveRequestDto recordDto);

    @Operation(description = "Lista todas a pautas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao listar pautas.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(arraySchema = @Schema(implementation = RecordResponseDto.class)))),

            @ApiResponse(responseCode = "500", description = "Resposta de erro de condição inesperada na aplicação que o impediu de atender à solicitação.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDataDto.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<RecordResponseDto>> findAll();
}
