package com.github.deividst.votos.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.github.deividst.votos.enums.RecordStatusEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

import static com.github.deividst.votos.utils.SwaggerSchemaConstants.DESCRIPTION_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.DESCRIPTION_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.ID_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.ID_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.RECORD_STATUS_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.RECORD_STATUS_EXAMPLE;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.REGISTER_DATE_DESCRIPTION;
import static com.github.deividst.votos.utils.SwaggerSchemaConstants.REGISTER_DATE_EXAMPLE;
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
public class RecordResponseDto {

    @Schema(required = true, description = ID_DESCRIPTION, example = ID_EXAMPLE)
    private Long id;

    @Schema(required = true, description = SUBJECT_DESCRIPTION, example = SUBJECT_EXAMPLE)
    @Size(max = 255)
    @NotBlank
    private String subject;

    @Schema(required = true, description = DESCRIPTION_DESCRIPTION, example = DESCRIPTION_EXAMPLE)
    @Size(max = 255)
    @NotBlank
    private String description;

    @Schema(required = true, description = RECORD_STATUS_DESCRIPTION, example = RECORD_STATUS_EXAMPLE)
    private RecordStatusEnum status;

    @Schema(required = true, description = SESSION_DESCRIPTION)
    private SessionResponseDto session;

    @Schema(required = true, description = REGISTER_DATE_DESCRIPTION, example = REGISTER_DATE_EXAMPLE)
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime registerDate;

}
