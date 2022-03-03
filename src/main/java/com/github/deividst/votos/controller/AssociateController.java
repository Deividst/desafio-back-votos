package com.github.deividst.votos.controller;

import com.github.deividst.votos.dtos.AssociateResponseDto;
import com.github.deividst.votos.dtos.AssociateSaveRequestDto;
import com.github.deividst.votos.dtos.ErrorDataDto;
import com.github.deividst.votos.dtos.RecordResponseDto;
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


public interface AssociateController {

    @Operation(description = "Realiza o cadastro de uma novo associado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sucesso ao cadastrar associado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = AssociateResponseDto.class))),

            @ApiResponse(responseCode = "400", description = "Erro no payload ao cadastrar associado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDataDto.class))),

            @ApiResponse(responseCode = "500", description = "Resposta de erro de condição inesperada na aplicação que o impediu de atender à solicitação.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDataDto.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<AssociateResponseDto> save(@Validated @RequestBody AssociateSaveRequestDto associateDto);

    @Operation(description = "Realiza a busca de um associado pelo cpf.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao buscar associado.",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(arraySchema = @Schema(implementation = RecordResponseDto.class)))),

            @ApiResponse(responseCode = "500", description = "Resposta de erro de condição inesperada na aplicação que o impediu de atender à solicitação.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDataDto.class)))
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<AssociateResponseDto> findByCpf(String cpf);
}
