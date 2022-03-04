package com.github.deividst.votos.controller;

import com.github.deividst.votos.dtos.ErrorDataDto;
import com.github.deividst.votos.dtos.VoteDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface VoteController {

    @Operation(description = "Realiza o processo de votação em uma pauta.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso ao realizar votação."),
            @ApiResponse(responseCode = "404", description = "Erro ao realizar votação.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDataDto.class))),

            @ApiResponse(responseCode = "400", description = "Erro no payload ao relizar votação.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDataDto.class))),

            @ApiResponse(responseCode = "500", description = "Resposta de erro de condição inesperada na aplicação que o impediu de atender à solicitação.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorDataDto.class)))
    })
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Void> processVote(@Validated @RequestBody VoteDto voteDto);

}
