package com.github.deividst.votos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

import static com.github.deividst.votos.utils.SwaggerSchemaConstants.ID_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.ID_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SESSION_FINAL_DATE_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SESSION_FINAL_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SESSION_INITIAL_DATE_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SESSION_INITIAL_EXAMPLE;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class SessionResponseDto {

    @Schema(required = true, description = ID_DESCRIPTION, example = ID_EXAMPLE)
    private Long id;

    @Schema(required = true, description = SESSION_INITIAL_DATE_DESCRIPTION, example = SESSION_INITIAL_EXAMPLE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime initialDate;

    @Schema(required = true, description = SESSION_FINAL_DATE_DESCRIPTION, example = SESSION_FINAL_EXAMPLE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime finalDate;

}
