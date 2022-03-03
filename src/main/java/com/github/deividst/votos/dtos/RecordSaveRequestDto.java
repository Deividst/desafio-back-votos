package com.github.deividst.votos.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

import static com.github.deividst.votos.utils.SwaggerSchemaConstants.DESCRIPTION_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.DESCRIPTION_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SESSION_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SUBJECT_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.SUBJECT_EXAMPLE;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RecordSaveRequestDto {

    @Schema(required = true, description = SUBJECT_DESCRIPTION, example = SUBJECT_EXAMPLE)
    @Size(max = 255)
    @NotBlank(message = "O campo subject é obrigatório")
    private String subject;

    @Schema(required = true, description = DESCRIPTION_DESCRIPTION, example = DESCRIPTION_EXAMPLE)
    @Size(max = 255)
    @NotBlank(message = "O campo description é obrigatório")
    private String description;

    @Schema(required = true, description = SESSION_DESCRIPTION)
    private SessionSaveRecordRequestDto session;

}
