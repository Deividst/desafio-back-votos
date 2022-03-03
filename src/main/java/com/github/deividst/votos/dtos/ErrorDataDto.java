package com.github.deividst.votos.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

import static com.github.deividst.votos.utils.SwaggerSchemaConstants.ERROR_MESSAGE_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.ERROR_MESSAGE_EXAMPLE;

@Getter
@Builder
public class ErrorDataDto {

    @Schema(description = ERROR_MESSAGE_DESCRIPTION, example = ERROR_MESSAGE_EXAMPLE)
    private final List<String> errorMessages;

}
